package dev.neuralnexus.taterlib.storage.databases;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/** MongoDB database class */
public class MongoDBDatabase implements Database<MongoClient> {
    private final Database.Type type = Database.Type.MONGODB;
    private final MongoClient connection;
    private final String database;

    /**
     * Constructor for the MongoDBDataSource class
     *
     * @param config The config data
     */
    public MongoDBDatabase(Database.DatabaseConfig config) {
        this.database = config.database;
        String URI =
                "mongodb://"
                        + config.username
                        + ":"
                        + config.password
                        + "@"
                        + config.host
                        + ":"
                        + (config.port == 0 ? 27017 : config.port)
                        + "/"
                        + database
                        + "?authSource=admin";
        connection = MongoClients.create(URI);
    }

    /** {@inheritDoc} */
    @Override
    public Database.Type type() {
        return type;
    }

    /** {@inheritDoc} */
    @Override
    public MongoClient connection() {
        return connection;
    }

    /** {@inheritDoc} */
    @Override
    public String database() {
        return database;
    }
}
