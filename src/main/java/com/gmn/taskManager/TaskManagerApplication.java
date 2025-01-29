package com.gmn.taskManager;

import com.gmn.taskManager.DTO.TaskDTO;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		// Start Spring Boot application
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
