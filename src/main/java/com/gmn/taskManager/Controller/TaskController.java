package com.gmn.taskManager.Controller;

import com.gmn.taskManager.DTO.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController {


    @GetMapping
    public String  getTasks() {
        return "";
    }
}
