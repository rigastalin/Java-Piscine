package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.exceptions.*;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> optionalMessage;
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String mesQuery = "SELECT * FROM chat.message WHERE id =" + id;
        ResultSet resultSet = statement.executeQuery(mesQuery);
        resultSet.next();

        User user = new User(1L, "parina", "petuhina", null, null);
        Chatroom chatroom = new Chatroom(1L, "chatroom", null, null);
        optionalMessage = Optional.of(new Message(resultSet.getLong(1), user, chatroom, resultSet.getString("message"), LocalDateTime.of(2022, 10, 19, 10, 23)));

        return optionalMessage;
    }

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        String saveQuery = "INSERT INTO chat.message (message, time, author, room_id)" +
                " VALUES (" +
                "'" + message.getText() + "'" + ", " +
                "'" + message.getLocalDateTime() + "'"  + ", " +
                message.getAuthor().getId() + ", " +
                message.getChatroom().getId() + ");";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS)){;

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            message.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
        return true;
    }
}
