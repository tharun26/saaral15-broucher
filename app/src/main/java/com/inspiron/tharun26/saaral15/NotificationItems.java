package com.inspiron.tharun26.saaral15;

/**
 * Created by tharun26 on 1/2/15.
 */
public class NotificationItems {
    private String message,time;

    public NotificationItems(String message) {
        this.message = message;
    }

    public NotificationItems(String message, String time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
