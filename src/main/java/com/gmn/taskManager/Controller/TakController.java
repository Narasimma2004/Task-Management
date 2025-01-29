package com.gmn.taskManager.Controller;

import com.gmn.taskManager.DTO.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class TakController {

    @Autowired
    TaskDTO taskDTO;
    @GetMapping
    public String  getTasks() {
        return ;
    }
}
