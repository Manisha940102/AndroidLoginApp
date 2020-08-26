package com.example.hr_project1;

public class Put {
    private int user_id;
    private String user_name;
    private String pass;
    private String email;

    public Put(int user_id, String user_name, String pass, String email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.pass = pass;
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
