package dev.neuralnexus.taterlib.common.storage;

import dev.dejvokep.boostedyaml.YamlDocument;

import java.util.Arrays;


public class DataSource {
    /**
     * Get the database
     * @param type The type of database
     * @param config The config file
     * @return The database
     */
    public static Database getDatabase(String type, YamlDocument config) {
        try {
            switch (type) {
                case "mysql":
                    return new MySQLDatabase(config);
                case "mongodb":
                    return new MongoDBDatabase(config);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}