package com.example.hr_project1;

class Response_Take {
   private int status;
   private String message;

    public Response_Take(int status, String message) {
        this.status = status;
        this.message = message;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
