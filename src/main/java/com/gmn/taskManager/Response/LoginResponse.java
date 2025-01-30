package com.gmn.taskManager.Response;

import com.gmn.taskManager.Entity.Users;

public class LoginResponse {

    boolean status;
    String message;
    Object users;

    public LoginResponse() {
    }

    public LoginResponse(boolean status, String message, Object users) {
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

    public Object getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
