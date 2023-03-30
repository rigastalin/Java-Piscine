package edu.school21.chat.models;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    protected Chatroom chatroom;
    private String text;
    private LocalDateTime localDateTime;

    public Message(Long id, User author, Chatroom chatroom, String text, LocalDateTime localDateTime) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(author, message.author) && Objects.equals(chatroom, message.chatroom) && Objects.equals(text, message.text) && Objects.equals(localDateTime, message.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, localDateTime);
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text='" + text + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
