package dev.neuralnexus.taterlib.common.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class MySQLDatabase extends Database<Connection> {
    /**
     * Class used to abstract the SQL data source.
     * config: The configuration for the SQL data source.
     * ds: The data source.
     */
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    /**
     * Constructor for the MySQLDataSource class
     * @param sql_config The configuration for the MySQL data source.
     */
    public MySQLDatabase(YamlDocument sql_config) {
        super("mysql", null, null);
        String host = sql_config.getString("storage.config.host");
        int port = Integer.parseInt(sql_config.getString("storage.config.port"));
        String database = sql_config.getString("storage.config.database");
        String username = sql_config.getString("storage.config.username");
        String password = sql_config.getString("storage.config.password");

        if (port == 0) {
            port = 3306;
        }
        String URI = "jdbc:mysql://" + host + ":" + port + "/" + database;
        config.setJdbcUrl(URI);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("ca.sperrer.p0t4t0sandwich.tatersync.lib.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);

        setConnection(getConnection());
        setDatabase(database);
    }

    @Override
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
