package com.example.hr_project1;

public class Take {
    private String serial_no;
    private int user_id;
    private int take_in_out;

    public Take (String serial_no,int user_id,int take_in_out) {
            this.serial_no = serial_no;
            this.user_id = user_id;
            this.take_in_out = take_in_out;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTake_in_out() {
        return take_in_out;
    }

    public void setTake_in_out(int take_in_out) {
        this.take_in_out = take_in_out;
    }
}
