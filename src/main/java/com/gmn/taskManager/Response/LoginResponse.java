package com.gmn.taskManager.Response;

import com.gmn.taskManager.Entity.Users;

public class LoginResponse {

    boolean status;
    String message;
    Users users;

    public LoginResponse() {
    }

    public LoginResponse(boolean status, String message, Users users) {
        this.status = status;
        this.message = message;
        this.users = users;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
