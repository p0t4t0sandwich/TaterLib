package dev.neuralnexus.taterlib.storage.databases;

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
    Database.Type type();

    /**
     * Get the connection
     *
     * @return The connection
     */
    T connection();

    /**
     * Get the database
     *
     * @return The database
     */
    String database();

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
