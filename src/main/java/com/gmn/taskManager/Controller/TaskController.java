package com.gmn.taskManager.Controller;

import com.gmn.taskManager.DTO.TaskDTO;
import com.gmn.taskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
