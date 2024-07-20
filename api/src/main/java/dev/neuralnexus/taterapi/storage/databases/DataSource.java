/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.storage.databases;

/** Data source class */
public class DataSource {
    /**
     * Get the database
     *
     * @param type The type of database
     * @param config The config data
     * @return The database
     */
    public static Database getDatabase(Database.Type type, Database.DatabaseConfig config) {
        try {
            switch (type) {
                case FILESYSTEM:
                    return new Filesystem(config);
                case MYSQL:
                    return new MySQLDatabase(config);
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
