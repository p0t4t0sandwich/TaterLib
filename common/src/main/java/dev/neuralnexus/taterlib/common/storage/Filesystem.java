package dev.neuralnexus.taterlib.common.storage;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;

import java.io.File;

/**
 * Filesystem database class
 */
public class Filesystem implements Database<String> {
    private final Database.Type type = Database.Type.FILESYSTEM;
    private final String connection;
    private final String database;

    /**
     * Constructor for the Filesystem class
     * @param config The config data
     */
    public Filesystem(Database.DatabaseConfig config) {
        this.connection = "." + File.separator + TaterAPIProvider.get().configFolder() + File.separator + config.host;
        this.database = config.database;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Database.Type getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConnection() {
        return connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDatabase() {
        return database;
    }
}
