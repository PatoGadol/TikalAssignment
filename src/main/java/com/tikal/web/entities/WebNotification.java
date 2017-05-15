package com.tikal.web.entities;

/**
 * Created by pavel_sopher on 10/05/2017.
 */
public class WebNotification {

    private String message;
    public WebNotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
