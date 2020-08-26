package com.example.hr_project1;

public class Post {
    private String user_name;
    private String pass;

   public Post(String name, String pass) {
        this.user_name = name;
        this.pass = pass;
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
}
