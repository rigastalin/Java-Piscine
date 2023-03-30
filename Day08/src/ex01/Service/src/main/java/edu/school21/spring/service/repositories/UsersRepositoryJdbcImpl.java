package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String findEmailQuery = "SELECT * FROM models.user WHERE email = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(findEmailQuery)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection(); 
             Statement statement = connection.createStatement()) {
            String idQuery = "SELECT * FROM models.user WHERE id = ";
            ResultSet resultSet = statement.executeQuery(idQuery + id);

            if (!resultSet.next()) {
                return null;
            }
            return new User(resultSet.getLong(1), resultSet.getString(2));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement= connection.createStatement()) {
            String findAllQuery = "SELECT * FROM models.user";
            ResultSet rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                users.add(new User(rs.getLong(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate("INSERT INTO models.user (id, email) VALUES ("
                    + entity.getId() + ", '"
                    + entity.getEmail() + "');");

            if (result == 0) {
                System.err.println("JDBC   ======> Not saved");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        String updateQuery = "UPDATE models.user SET email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                System.err.println("JDBC   ======> Not updated");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM models.user WHERE id = ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery + id)) {
            int result = statement.executeUpdate();
            if (result == 0) {
                System.err.println("JDBC   ======> Not found");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
