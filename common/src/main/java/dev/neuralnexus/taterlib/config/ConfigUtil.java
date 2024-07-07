/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.taterapi.logger.Logger;

import io.leangen.geantyref.TypeToken;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/** Config utilities. */
public class ConfigUtil {
    public static CommentedConfigurationNode getRoot(
            HoconConfigurationLoader loader, Logger logger) {
        try {
            return loader.load();
        } catch (ConfigurateException e) {
            logger.error("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            return null;
        }
    }

    public static <T> T get(
            CommentedConfigurationNode root, TypeToken<T> typeToken, String path, Logger logger) {
        try {
            return root.node(path).get(typeToken);
        } catch (SerializationException e) {
            logger.error(
                    "An error occurred while loading the modules configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            return null;
        }
    }

    public static <T> void set(
            CommentedConfigurationNode root,
            TypeToken<T> typeToken,
            String path,
            T value,
            Logger logger) {
        try {
            root.node(path).set(typeToken, value);
        } catch (SerializationException e) {
            logger.error(
                    "An error occurred while saving the modules configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    /** Copy the default configuration to the config folder. */
    public static <T> void copyDefaults(
            Class<T> clazz, Path configPath, String defaultConfigPath, Logger logger) {
        if (configPath.toFile().exists()) {
            return;
        }
        try {
            Files.createDirectories(configPath.getParent());
            Files.copy(
                    Objects.requireNonNull(
                            clazz.getClassLoader().getResourceAsStream(defaultConfigPath)),
                    configPath);
        } catch (IOException e) {
            logger.error(
                    "An error occurred while copying the default configuration: " + e.getMessage());
            e.printStackTrace();
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }
}
