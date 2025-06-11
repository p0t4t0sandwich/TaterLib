/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.bukkit;

import static dev.neuralnexus.taterapi.util.PathUtils.getPluginsFolder;
import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForMethod;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.JavaLogger;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.ModInfoImpl;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Bukkit platform */
public final class BukkitMeta implements Platform.Meta {
    @Override
    public @NotNull Object server() {
        return Bukkit.getServer();
    }

    @Override
    public @NotNull Object client() {
        throw new UnsupportedOperationException("Bukkit does not run on the client");
    }

    @Override
    public @NotNull Object minecraft() {
        try {
            String clazz = Bukkit.getServer().getClass().getPackage().getName() + ".CraftServer";
            Class<?> craftServer = Class.forName(clazz);
            return craftServer.getDeclaredMethod("getServer").invoke(Bukkit.getServer());
        } catch (ClassNotFoundException
                | InvocationTargetException
                | IllegalAccessException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Side side() {
        return Side.SERVER;
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        String version = Bukkit.getVersion();
        if (MetaAPI.instance().isPlatformPresent(Platforms.PAPER)
                && checkForMethod("org.bukkit.Bukkit", "getMinecraftVersion")) {
            version = PaperMeta.getMinecraftVersion();
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public @NotNull String loaderVersion() {
        return Bukkit.getBukkitVersion();
    }

    @Override
    public @NotNull String apiVersion() {
        return Bukkit.getBukkitVersion();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        return Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                .map(
                        plugin ->
                                new ModInfoImpl(
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getVersion(),
                                        Platforms.BUKKIT))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        return new JavaLogger(modId, Bukkit.getLogger());
    }

    @Override
    public @NotNull Path modFolder() {
        return getPluginsFolder();
    }

    @Override
    public @NotNull Path configFolder() {
        return getPluginsFolder();
    }
}
