/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getPluginsFolder;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.ModInfoImpl;
import dev.neuralnexus.modapi.metadata.impl.logger.JavaLogger;

import org.bukkit.Bukkit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Bukkit platform */
public final class BukkitMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(Bukkit.getServer().getVersion());
    }

    @Override
    public String loaderVersion() {
        return Bukkit.getServer().getVersion();
    }

    @Override
    public Mappings mappings() {
        if (Platforms.isPaper() && this.minecraftVersion().isAtLeast(MinecraftVersions.V20_6)) {
            return Mappings.MOJMAP;
        } else if (Platforms.isSpigot()) {
            return Mappings.SPIGOT;
        }
        return Mappings.OFFICIAL;
    }

    @Override
    public List<ModInfo> modList() {
        return Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                .map(
                        plugin ->
                                new ModInfoImpl(
                                        plugin.getName(),
                                        plugin.getName(),
                                        plugin.getDescription().getVersion(),
                                        Platforms.BUKKIT))
                .collect(Collectors.toList());
    }

    @Override
    public Logger<java.util.logging.Logger> logger(String modId) {
        return new JavaLogger(modId, Bukkit.getLogger());
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
