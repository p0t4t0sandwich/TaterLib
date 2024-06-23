package dev.neuralnexus.taterlib.storage.databases;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;

import java.io.File;

/** Filesystem database class */
public class Filesystem implements Database<String> {
    private final Database.Type type = Database.Type.FILESYSTEM;
    private final String connection;
    private final String database;

    /**
     * Constructor for the Filesystem class
     *
     * @param config The config data
     */
    public Filesystem(Database.DatabaseConfig config) {
        this.connection =
                TaterAPIProvider.platformData().configFolder()
                        + File.separator
                        + config.host;
        this.database = config.database;
    }

    /** {@inheritDoc} */
    @Override
    public Database.Type type() {
        return type;
    }

    /** {@inheritDoc} */
    @Override
    public String connection() {
        return connection;
    }

    /** {@inheritDoc} */
    @Override
    public String database() {
        return database;
    }
}
