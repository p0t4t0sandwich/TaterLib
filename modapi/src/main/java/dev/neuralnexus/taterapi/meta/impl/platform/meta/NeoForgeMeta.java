/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.Slf4jLogger;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.WMinecraft;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the NeoForge platform */
public final class NeoForgeMeta implements Platform.Meta {
    @Override
    public @NotNull Object server() {
        return this.minecraft();
    }

    @Override
    public @NotNull Object client() {
        return WMinecraft.getInstance();
    }

    @Override
    public @NotNull Object minecraft() {
        if (this.side().isClient() && WMinecraft.hasServer()) {
            return WMinecraft.getServer();
        }
        return ServerLifecycleHooks.getCurrentServer();
    }

    @Override
    public @NotNull Side side() {
        return WMinecraft.determineSide(this.isClient());
    }

    @Override
    public boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(FMLLoader.versionInfo().mcVersion());
    }

    @Override
    public @NotNull String loaderVersion() {
        return FMLLoader.versionInfo().fmlVersion();
    }

    @Override
    public @NotNull String apiVersion() {
        return FMLLoader.versionInfo().neoForgeVersion();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        List<net.neoforged.fml.loading.moddiscovery.ModInfo> mods = ModList.get().getMods();
        if (mods == null || mods.isEmpty()) {
            mods = LoadingModList.get().getMods();
        }
        return mods.stream()
                .map(
                        modContainer ->
                                new ModInfoImpl(
                                        modContainer.getModId(),
                                        modContainer.getDisplayName(),
                                        modContainer.getVersion().toString(),
                                        Platforms.NEOFORGE))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        return new Slf4jLogger(modId);
    }
}
