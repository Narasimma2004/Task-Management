package com.gmn.taskManager.Service;

import com.gmn.taskManager.DTO.TaskDTO;
import com.gmn.taskManager.Repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JWTService jwtService;

    @Autowired
    UsersRepository usersRepository;

    public ResponseEntity<String> addTask(TaskDTO taskDTO) {
        try {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);
        int userId = usersRepository.findByName(username).getUserId();
        String synthesisName = username.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();

            String tableName = "user_" + synthesisName+userId;
            String insertQuery = "INSERT INTO " + tableName + " (task_name, task_status, startDate, endDate) VALUES (?, ?, ?, ?)";
            LocalDate startDate = LocalDate.now();
            jdbcTemplate.update(insertQuery,taskDTO.getTaskName(), taskDTO.getTaskStatus(),startDate,taskDTO.getEndDate());
            return ResponseEntity.ok("Task Added");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Task Added to be Failed");
        }

    }

    public ResponseEntity<List<Map<String, Object>>> showTask() {
        try {

            String authHeader = request.getHeader("Authorization");
            String token = authHeader.substring(7);
            String userName = jwtService.extractUserName(token);
            int userId = usersRepository.findByName(userName).getUserId();
            String synthesisName = userName.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
            String tableName = "user_" + synthesisName + userId;

            String selectQuery = "SELECT task_id, task_name, task_status, startDate, EndDate FROM " + tableName;

            return ResponseEntity.ok(jdbcTemplate.queryForList(selectQuery));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public ResponseEntity<TaskDTO> updateTask(TaskDTO taskDTO, int task_id) {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = authHeader.substring(7);
            String userName = jwtService.extractUserName(token);
            int userId = usersRepository.findByName(userName).getUserId();
            String synthesisName = userName.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
            String tableName = "user_" + synthesisName + userId;

            String updateQuery = "UPDATE " + tableName + " SET task_name = ?, task_status = ?, startDate = ?, endDate = ? WHERE task_id = ?";

            System.out.println(taskDTO.getTaskId()+"  "+taskDTO.getTaskName()+"  "+taskDTO.getTaskStatus());
            int affectedRows = jdbcTemplate.update(updateQuery,taskDTO.getTaskName(),taskDTO.getTaskStatus(),taskDTO.getStartDate(),taskDTO.getEndDate(), task_id);

            System.out.println(affectedRows+" row/s affected");
            return ResponseEntity.ok(taskDTO);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> deleteTask(int taskId) {
        try{

            String authHeader = request.getHeader("Authorization");
            String token = authHeader.substring(7);
            String userName = jwtService.extractUserName(token);
            int userId = usersRepository.findByName(userName).getUserId();
            String synthesisName = userName.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
            String tableName = "user_" + synthesisName + userId;


            String deleteQuery = "DELETE FROM "+ tableName +" WHERE task_id = ?";
            int affectedRows = jdbcTemplate.update(deleteQuery, taskId);

            String selectSql = "SELECT task_id FROM "+ tableName +" ORDER BY task_id";
            List<Integer> remainingIds = jdbcTemplate.queryForList(selectSql, Integer.class);

            int newId = 1;
            for (Integer oldId : remainingIds) {
                String updateSql = "UPDATE "+ tableName +" SET task_id = ? WHERE task_id = ?";
                jdbcTemplate.update(updateSql, newId++, oldId);
            }

            String resetAutoIncrementQuery = "ALTER TABLE " + tableName + " AUTO_INCREMENT = ?";
            jdbcTemplate.update(resetAutoIncrementQuery, newId);

            return ResponseEntity.ok(affectedRows+" row/s affected");

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }
}
