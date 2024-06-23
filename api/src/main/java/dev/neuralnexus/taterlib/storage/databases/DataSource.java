package dev.neuralnexus.taterlib.storage.databases;

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
                case MONGODB:
                    return new MongoDBDatabase(config);
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
