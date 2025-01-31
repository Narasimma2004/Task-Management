package com.gmn.taskManager.Controller;

import com.gmn.taskManager.DTO.TaskDTO;
import com.gmn.taskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/secure")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add-task")
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO)
    {
        return taskService.addTask(taskDTO);
    }

    @GetMapping("/show-task")
    public ResponseEntity<List<Map<String, Object>>> showTask()
    {
        return taskService.showTask();
    }

    @PutMapping("/update-task/{task_id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable("task_id") int task_id)
    {
        return taskService.updateTask(taskDTO, task_id);
    }

    @DeleteMapping("/delete-task/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable("task_id") int task_id)
    {
        return taskService.deleteTask(task_id);
    }
}
