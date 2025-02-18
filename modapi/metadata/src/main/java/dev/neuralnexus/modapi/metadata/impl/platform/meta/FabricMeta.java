/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.modapi.metadata.impl.WMinecraft;
import dev.neuralnexus.modapi.metadata.impl.logger.ApacheLogger;
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Stores data about the Fabric platform */
public final class FabricMeta implements Platform.Meta {
    @Override
    public @NotNull Object server() {
        return this.minecraft();
    }

    @Override
    public @NotNull Object client() {
        return WMinecraft.getInstance();
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull Object minecraft() {
        if (this.side().isClient() && WMinecraft.hasServer()) {
            return WMinecraft.getServer();
        }
        return FabricLoader.getInstance().getGameInstance();
    }

    @Override
    public @NotNull Side side() {
        return WMinecraft.determineSide(
                FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public @NotNull MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(
                FabricLoader.getInstance()
                        .getModContainer("minecraft")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString());
    }

    @Override
    public @NotNull String loaderVersion() {
        Optional<ModContainer> container =
                FabricLoader.getInstance().getModContainer("fabric-loader");
        if (container.isPresent()) {
            return container.get().getMetadata().getVersion().getFriendlyString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public @NotNull String apiVersion() {
        Optional<ModContainer> container =
                FabricLoader.getInstance().getModContainer("fabric-api-base");
        if (container.isPresent()) {
            return container.get().getMetadata().getVersion().getFriendlyString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        return FabricLoader.getInstance().getAllMods().stream()
                .map(
                        modContainer ->
                                new ModInfoImpl(
                                        modContainer.getMetadata().getId(),
                                        modContainer.getMetadata().getName(),
                                        modContainer.getMetadata().getVersion().getFriendlyString(),
                                        Platforms.FABRIC))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersions.V18)) {
            return new ApacheLogger(modId);
        }
        return new Slf4jLogger(modId);
    }
}
