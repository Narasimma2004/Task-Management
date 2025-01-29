package com.gmn.taskManager.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
public class TaskDTO {

    @Id
    private String taskId;
    private String taskName;
    private String taskStatus;
    private LocalDateTime startDate;
    private String endDate;


    public TaskDTO(String taskId, String taskName, String taskStatus, LocalDateTime startDate, String endDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TaskDTO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", startDate=" + startDate +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
        System.out.println("PrePersist startDate: " + startDate);
    }
}
