package dev.neuralnexus.taterapi.common.storage;

public class Database<T> {
    private final String type;
    private T connection;
    private String database;

    public Database(String type, T connection, String database) {
        this.type = type;
        this.connection = connection;
        this.database = database;
    }

    public String getType() {
        return type;
    }

    public T getConnection() {
        return connection;
    }

    public String getDatabase() {
        return database;
    }

    public void setConnection(T connection) {
        this.connection = connection;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
