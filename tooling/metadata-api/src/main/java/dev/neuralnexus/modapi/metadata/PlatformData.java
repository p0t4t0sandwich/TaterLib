/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getConfigFolder;
import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getModsFolder;

import dev.neuralnexus.modapi.metadata.impl.data.PlatformDataImpl;
import dev.neuralnexus.modapi.metadata.logger.Logger;

import java.nio.file.Path;
import java.util.List;

public interface PlatformData {
    static PlatformData instance() {
        return PlatformDataImpl.getInstance();
    }

    /**
     * Get the running Minecraft version
     *
     * @return The running Minecraft version
     */
    MinecraftVersion minecraftVersion();

    /**
     * Get the modloader version
     *
     * @return the modloader version
     */
    String modLoaderVersion();

    /**
     * Get the platform's mappings
     *
     * @return The platform's mappings
     */
    Mappings mappings();

    /**
     * Get the mod list
     *
     * @return The mod list
     */
    List<ModInfo> modList();

    /**
     * Get the Logger
     *
     * @return The Logger
     */
    Logger logger(String pluginId);

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started
     *
     * @param nameOrId The name of the plugin or modId of the mod
     */
    default boolean isModLoaded(String nameOrId) {
        return modList().stream()
                .anyMatch(
                        modInfo ->
                                modInfo.id().equals(nameOrId) || modInfo.name().equals(nameOrId));
    }

    /**
     * Get the path to the mod/plugin folder
     *
     * @return The path to the mod/plugin folder
     */
    default Path modFolder() {
        return getModsFolder();
    }

    /**
     * Get the path to the config folder
     *
     * @return The path to the config folder
     */
    default Path configFolder() {
        return getConfigFolder();
    }
}
