/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metadata.impl;

import static dev.neuralnexus.taterapi.util.PathUtils.getPluginsFolder;

import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import org.bukkit.Bukkit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Bukkit platform */
public class BukkitData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(Bukkit.getServer().getVersion());
    }

    @Override
    public String modLoaderVersion() {
        return Bukkit.getServer().getVersion();
    }

    @Override
    public Mappings mappings() {
        return Mappings.OFFICIAL;
    }

    @Override
    public List<ModInfo> modList() {
        return Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                .map(
                        plugin ->
                                new ModInfo(
                                        plugin.getName(),
                                        plugin.getName(),
                                        plugin.getDescription().getVersion()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        return new LoggerAdapter(pluginId, Bukkit.getLogger());
    }

    @Override
    public Path modFolder() {
        return getPluginsFolder();
    }

    @Override
    public Path configFolder() {
        return getPluginsFolder();
    }
}
