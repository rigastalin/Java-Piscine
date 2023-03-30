package edu.school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;

import edu.school21.spring.service.models.User;
import edu.school21.spring.service.repositories.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user "
                    + "(id integer not null, email varchar(50) not null);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        Long id = 1L;
        for (int i = 0; i < 3; i++) {
            usersRepositoryJdbc.save(new User(id, "parina" + id++ + "@petuhina.ru"));
            usersRepositoryJdbcTemplate.save(new User(id, "parina" + id++ + "@petuhina.ru"));
        }


        System.out.println("JDBC   ======>   LIST OF USERS: " + usersRepositoryJdbc.findAll());
        System.out.println("SPRING ======>   LIST OF USERS: " + usersRepositoryJdbcTemplate.findAll());
        System.out.println();
        usersRepositoryJdbc.delete(1L);
        usersRepositoryJdbcTemplate.delete(3L);
        System.out.println("JDBC   ======>   DELETE User1: " + usersRepositoryJdbc.findAll());
        System.out.println("SPRING ======>   DELETE User1: " + usersRepositoryJdbcTemplate.findAll());
        System.out.println();

        User upDate = usersRepositoryJdbc.findById(2L);
        User upDate2 = usersRepositoryJdbcTemplate.findById(4L);
        upDate.setEmail("phuck@uuuuu.ru");
        upDate2.setEmail("phuck@uuuuuTemplate.ru");
        usersRepositoryJdbc.update(upDate);
        usersRepositoryJdbcTemplate.update(upDate2);
        System.out.println("JDBC   ======>   AFTER UPDATE: " + usersRepositoryJdbc.findAll());
        System.out.println("SPRING ======>   AFTER UPDATE: " + usersRepositoryJdbcTemplate.findAll());
        System.out.println();

        System.out.println("JDBC   ======>   LOOKING FOR USER WITH EMAIL = phuck@uuuuu.ru: " + usersRepositoryJdbc.findByEmail("phuck@uuuuu.ru:"));
        System.out.println("SPRING ======>   LOOKING FOR USER WITH EMAIL = phuck@uuuuu.ru: " + usersRepositoryJdbcTemplate.findByEmail("phuck@uuuuu.ru:"));
        System.out.println();

        System.out.println("JDBC   ======>   LOOKING FOR NOT EXISTED USER " + usersRepositoryJdbc.findByEmail("chponks@zhopa.ru"));
        System.out.println("SPRING ======>   LOOKING FOR NOT EXISTED USER " + usersRepositoryJdbcTemplate.findByEmail("chponks@zhopa.ru"));
        System.out.println();


        System.out.println("UPDATE USER WHICH DOESNT EXIST");
        User user10 = new User(33L, "chikibriki@phucku.ru");
        User user11 = new User(666L, "chikibriki@phucku.ru");
        usersRepositoryJdbc.update(user10);
        usersRepositoryJdbcTemplate.update(user11);
        usersRepositoryJdbc.delete(33L);
        usersRepositoryJdbcTemplate.delete(666L);
    }
}
