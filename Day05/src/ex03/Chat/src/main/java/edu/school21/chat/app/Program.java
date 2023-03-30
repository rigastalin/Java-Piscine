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

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariDataSource);
        User author = new User(2L, "Pedrinjo", "Pedruhinjo", new ArrayList(), new ArrayList());

        Message message = messagesRepository.findById(2L).get();
        message.setText("srat");
        message.setLocalDateTime(LocalDateTime.now());
        message.setAuthor(author);

        messagesRepository.update(message);
        hikariDataSource.close();
    }
}

