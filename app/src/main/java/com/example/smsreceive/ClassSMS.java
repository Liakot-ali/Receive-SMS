package com.example.smsreceive;

public class ClassSMS {
    String id, time, date, body, sender;
    int seen;

    public ClassSMS(String id, String time, String date, String body, String sender, int seen) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.body = body;
        this.sender = sender;
        this.seen = seen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }
}
