/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.storage.databases;

import dev.neuralnexus.taterapi.TaterAPIProvider;

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
                TaterAPIProvider.platformData().configFolder() + File.separator + config.host;
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
