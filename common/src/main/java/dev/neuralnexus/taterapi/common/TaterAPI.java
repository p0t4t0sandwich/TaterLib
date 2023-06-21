package dev.neuralnexus.taterapi.common;

import dev.neuralnexus.taterapi.common.storage.DataSource;
import dev.neuralnexus.taterapi.common.storage.Database;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TaterAPI {
    /**
     * Properties of the TaterAPI class.
     * config: The config file
     * logger: The logger
     * singleton: The singleton instance of the TaterAPI class
     * STARTED: Whether the PanelServerManager has been started
     */
    private static YamlDocument config;
    private final Object logger;
    private static TaterAPI singleton = null;
    private boolean STARTED = false;
    public Database database;

    /**
     * Constructor for the TaterAPI class.
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public TaterAPI(String configPath, Object logger) {
        singleton = this;
        this.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("./" + configPath + "/TaterAPI", "config.yml"),
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            useLogger("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Getter for the singleton instance of the PanelServerManager class.
     * @return The singleton instance
     */
    public static TaterAPI getInstance() {
        return singleton;
    }

    /**
     * Use whatever logger is being used.
     * @param message The message to log
     */
    public void useLogger(String message) {
        if (logger instanceof java.util.logging.Logger) {
            ((java.util.logging.Logger) logger).info(message);
        } else if (logger instanceof org.slf4j.Logger) {
            ((org.slf4j.Logger) logger).info(message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Start TaterAPI
     */
    public void start() {
        if (STARTED) {
            useLogger("TaterAPI has already started!");
            return;
        }
        STARTED = true;

        String type = config.getString("storage.type");
        database = DataSource.getDatabase(type, config);

        useLogger("TaterAPI has been started!");
    }

    /**
     * Get the server name from the config
     * @return The server name
     */
    public static String getServerName() {
        return config.getString("server.name");
    }
}
