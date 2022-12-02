package ru.tinkoff.academy.tinkofflibrary.integration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.tinkoff.academy.tinkofflibrary.Containers;

public class TestEnvironment {

    public static HikariDataSource hikariDataSource;

    static {
        initializeHikariDataSource();
    }

    private static void initializeHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(Containers.postgre.getJdbcUrl());
        hikariConfig.setUsername(Containers.postgre.getUsername());
        hikariConfig.setPassword(Containers.postgre.getPassword());
        hikariConfig.setPoolName("tcspool");
        hikariConfig.setAutoCommit(true);
        hikariConfig.setMaximumPoolSize(6);

        hikariDataSource = new HikariDataSource(hikariConfig);
    }

}
