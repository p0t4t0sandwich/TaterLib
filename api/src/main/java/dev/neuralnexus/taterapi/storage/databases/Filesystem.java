/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.storage.databases;

import dev.neuralnexus.taterapi.meta.MetaAPI;

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
        this.connection = MetaAPI.instance().meta().configFolder() + File.separator + config.host;
        this.database = config.database;
    }

    @Override
    public Database.Type type() {
        return type;
    }

    @Override
    public String connection() {
        return connection;
    }

    @Override
    public String database() {
        return database;
    }
}
