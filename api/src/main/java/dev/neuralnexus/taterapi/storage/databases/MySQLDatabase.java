/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.storage.databases;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/** MySQL database class */
public class MySQLDatabase implements Database<Connection> {
    private static final HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource ds;
    private final Database.Type type = Database.Type.MYSQL;
    private final Connection connection = null;
    private final String database;

    /**
     * Constructor for the MySQLDataSource class
     *
     * @param config The config data
     */
    public MySQLDatabase(Database.DatabaseConfig config) {
        this.database = config.database;

        String URI =
                "jdbc:mysql://"
                        + config.host
                        + ":"
                        + (config.port == 0 ? 3306 : config.port)
                        + "/"
                        + config.database;
        hikariConfig.setJdbcUrl(URI);
        hikariConfig.setUsername(config.username);
        hikariConfig.setPassword(config.password);
        hikariConfig.setDriverClassName("dev.neuralnexus.taterlib.lib.mysql.cj.jdbc.Driver");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(hikariConfig);
    }

    @Override
    public Database.Type type() {
        return type;
    }

    @Override
    public Connection connection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    public String database() {
        return database;
    }
}
