package dev.neuralnexus.taterlib.common.storage;

/**
 * Database interface
 *
 * @param <T> The type of connection
 */
public interface Database<T> {
    /**
     * Get the type of the database
     *
     * @return The type of the database
     */
    Database.Type getType();

    /**
     * Get the connection
     *
     * @return The connection
     */
    T getConnection();

    /**
     * Get the database
     *
     * @return The database
     */
    String getDatabase();

    /** Database type enum */
    enum Type {
        FILESYSTEM,
        MONGODB,
        MYSQL
    }

    /** Database config class */
    class DatabaseConfig {
        public final String host;
        public final int port;
        public final String database;
        public final String username;
        public final String password;

        public DatabaseConfig(
                String host, int port, String database, String username, String password) {
            this.host = host;
            this.port = port;
            this.database = database;
            this.username = username;
            this.password = password;
        }
    }
}
