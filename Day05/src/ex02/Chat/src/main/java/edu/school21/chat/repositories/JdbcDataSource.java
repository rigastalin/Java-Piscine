package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDataSource {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private HikariDataSource hikariDataSource;

    public JdbcDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
