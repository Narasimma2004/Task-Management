package com.gmn.taskManager.Service;

import com.gmn.taskManager.DTO.TaskDTO;
import com.gmn.taskManager.Repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
