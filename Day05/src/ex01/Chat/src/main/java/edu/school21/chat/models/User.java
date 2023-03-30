package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRoom;
    private List<Chatroom> usedRoom;

    public User(Long id, String login, String password, List<Chatroom> createdRoom, List<Chatroom> usedRoom) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRoom = createdRoom;
        this.usedRoom = usedRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createdRoom, user.createdRoom) && Objects.equals(usedRoom, user.usedRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRoom, usedRoom);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedRoom(List<Chatroom> createdRoom) {
        this.createdRoom = createdRoom;
    }

    public void setUsedRoom(List<Chatroom> usedRoom) {
        this.usedRoom = usedRoom;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getCreatedRoom() {
        return createdRoom;
    }

    public List<Chatroom> getUsedRoom() {
        return usedRoom;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRoom=" + createdRoom +
                ", usedRoom=" + usedRoom +
                '}';
    }
}