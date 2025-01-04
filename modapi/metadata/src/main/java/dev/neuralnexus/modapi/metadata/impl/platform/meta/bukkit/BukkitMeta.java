/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta.bukkit;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getPluginsFolder;
import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForMethod;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.logger.JavaLogger;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.ModInfoImpl;

import org.bukkit.Bukkit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Bukkit platform */
public final class BukkitMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = Bukkit.getVersion();
        if (MetaAPI.instance().isPlatformPresent(Platforms.PAPER)
                && checkForMethod("org.bukkit.Bukkit", "getMinecraftVersion")) {
            version = PaperMeta.getMinecraftVersion();
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public String loaderVersion() {
        return Bukkit.getBukkitVersion();
    }

    @Override
    public String apiVersion() {
        return Bukkit.getBukkitVersion();
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
    public Logger logger(String modId) {
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
