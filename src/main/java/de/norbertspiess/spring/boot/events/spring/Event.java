package de.norbertspiess.spring.boot.events.spring;

public class Event {
    private String msg;

    public Event(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
