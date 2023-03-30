package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("postgres");

        User creator = new User(1L, "Pedrina", "Pedruhina", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom chatroom = new Chatroom(1L, "Pedrinogram", creator, new ArrayList());
        Message message = new Message(null, author, chatroom, "My name is Pedrina", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariDataSource);
        messagesRepository.save(message);

        System.out.println(message.getId());
    }
}
