package com.example.hr_project1;

public class AddUser {
    private String user_name;
    private String pass;
    private String email;

    public AddUser(String user_name, String pass, String email) {
        this.user_name = user_name;
        this.pass = pass;
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
