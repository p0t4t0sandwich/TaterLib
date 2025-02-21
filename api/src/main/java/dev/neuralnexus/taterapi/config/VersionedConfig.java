/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.config;

import dev.neuralnexus.taterapi.logger.Logger;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;
import org.spongepowered.configurate.objectmapping.meta.Required;

@ConfigSerializable
public class VersionedConfig {
    @Comment("Config version, DO NOT CHANGE THIS")
    @Required
    private int version;

    public int version() {
        return version;
    }

    /**
     * Attempt to get the version of the configuration.
     *
     * @param node The configuration node to get the version from.
     * @param logger The logger to log errors to.
     * @return The version of the configuration, or 0 if it could not be loaded.
     */
    public static int tryGetVersion(ConfigurationNode node, Logger logger) {
        try {
            VersionedConfig vConfig = node.get(VersionedConfig.class);
            if (vConfig != null) {
                return vConfig.version();
            } else {
                logger.error("Failed to load the configuration version");
            }
        } catch (Exception e) {
            logger.error(
                    "An error occurred while loading the configuration version: " + e.getMessage());
            if (e.getCause() != null) {
                logger.error("Caused by: ", e.getCause());
            }
        }
        return 0;
    }
}
