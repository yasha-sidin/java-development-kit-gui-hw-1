package model;

public class Message {
    private String senderName;
    private String body;
    public Message(String senderName, String body) {
        this.senderName = senderName;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getSenderName() {
        return senderName;
    }

    @Override
    public String toString() {
        return senderName + ": " + body;
    }
}
