/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.taterapi.config.VersionedConfig;
import dev.neuralnexus.taterlib.config.versions.TaterLibConfig_V1;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/** A class for loading TaterLib configuration. */
public class TaterLibConfigLoader {
    private static final Logger logger = Logger.create("TaterLibConfigLoader");
    private static final Path configPath =
            Paths.get(
                    MetaAPI.instance().meta().configFolder()
                            + File.separator
                            + LoaderImpl.PROJECT_ID
                            + File.separator
                            + LoaderImpl.PROJECT_ID
                            + ".conf");
    private static HoconConfigurationLoader loader;
    private static TaterLibConfig config;

    /** Load the configuration from the file. */
    public static void load() {
        loader = HoconConfigurationLoader.builder()
                .path(configPath)
                .build();
        CommentedConfigurationNode node = null;
        try {
            node = loader.load();
        } catch (ConfigurateException e) {
            logger.error("An error occurred while loading the configuration: " + e.getMessage());
            if (e.getCause() != null) {
                logger.error("Caused by: ", e.getCause());
            }
        }
        if (node == null) {
            return;
        }

        int version = VersionedConfig.tryGetVersion(node, logger);
        switch (version) {
            case 1:
                try {
                    config = node.get(TaterLibConfig_V1.class);
                } catch (SerializationException e) {
                    logger.error("An error occurred while loading the modules configuration: " + e.getMessage());
                    if (e.getCause() != null) {
                        logger.error("Caused by: ", e.getCause());
                    }
                }
                break;
            default:
                logger.error("Unknown configuration version: " + version + ", defaulting to version 1");
                config = new TaterLibConfig_V1();
                try {
                    node.set(TaterLibConfig_V1.class, config);
                } catch (SerializationException e) {
                    logger.error("An error occurred while updating the configuration: " + e.getMessage());
                    if (e.getCause() != null) {
                        logger.error("Caused by: ", e.getCause());
                    }
                }
        }

        try {
            loader.save(node);
        } catch (ConfigurateException e) {
            logger.error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                logger.error("Caused by: ", e.getCause());
            }
        }
    }

    /** Unload the configuration. */
    public static void unload() {
        config = null;
    }

    /** Save the configuration to the file. */
    public static void save() {
        if (config == null) {
            return;
        }
        if (loader == null) {
            return;
        }
        CommentedConfigurationNode node = null;
        try {
            node = loader.load();
        } catch (ConfigurateException e) {
            logger.error("An error occurred while loading the configuration: " + e.getMessage());
            if (e.getCause() != null) {
                logger.error("Caused by: ", e.getCause());
            }
        }
        if (node == null) {
            return;
        }

        switch (config.version()) {
            case 1:
                try {
                    node.set(TaterLibConfig_V1.class, config);
                } catch (SerializationException e) {
                    logger.error("An error occurred while updating the configuration: " + e.getMessage());
                    if (e.getCause() != null) {
                        logger.error("Caused by: ", e.getCause());
                    }
                }
                break;
            default:
                logger.error("Unknown configuration version: " + config.version() + ", defaulting to version 1");
                try {
                    node.set(TaterLibConfig_V1.class, config);
                } catch (SerializationException e) {
                    logger.error("An error occurred while updating the configuration: " + e.getMessage());
                    if (e.getCause() != null) {
                        logger.error("Caused by: ", e.getCause());
                    }
                }
        }

        try {
            loader.save(node);
        } catch (ConfigurateException e) {
            logger.error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                logger.error("Caused by: ", e.getCause());
            }
        }
    }

    /**
     * Get the loaded configuration.
     *
     * @return The loaded configuration.
     */
    public static TaterLibConfig config() {
        if (config == null) {
            load();
        }
        return config;
    }
}
