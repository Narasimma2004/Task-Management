package com.gmn.taskManager.DTO;

public class LoginDTO {

    String eMail;
    String password;

    public LoginDTO() {
    }

    public LoginDTO(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
