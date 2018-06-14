package ru.ilgiz.matica.events;


public class MessageEvent {
    private int message;

    public MessageEvent() {
    }

    public MessageEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return this.message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
