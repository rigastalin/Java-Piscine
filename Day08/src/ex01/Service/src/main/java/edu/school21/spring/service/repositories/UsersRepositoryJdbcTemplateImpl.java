package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        String findIdQuery = "SELECT * FROM models.user WHERE id = :id";
        return jdbcTemplate.query(findIdQuery,
                new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        String findAllQuery = "SELECT * FROM models.user";
        List<User> users = jdbcTemplate.query(findAllQuery, new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        String saveQuery = "INSERT INTO models.user (id, email) VALUES (:id, :email)";
        if (jdbcTemplate.update(saveQuery, new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("email", entity.getEmail())) == 0)
        {
            System.err.println("SPRING ======> Not saved");
        }
    }

    @Override
    public void update(User entity) {
        String updateQuery = "UPDATE models.user SET email = :email WHERE id = :id";
        if (jdbcTemplate.update(updateQuery, new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("email", entity.getEmail())) == 0)
        {
            System.err.println("SPRING ======> Not updated");
        }
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM models.user WHERE id = :id";
        if (jdbcTemplate.update(deleteQuery, new MapSqlParameterSource()
                .addValue("id", id)) == 0)
        {
            System.err.println("SPRING ======> Not found");
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String findEmailQuery = "SELECT * FROM models.user WHERE email = :email";
        User user = jdbcTemplate.query(findEmailQuery,
                new MapSqlParameterSource().addValue("email", email),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        return Optional.ofNullable(user);
    }
}
