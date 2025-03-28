/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.ApacheLogger;
import dev.neuralnexus.taterapi.logger.impl.Slf4jLogger;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.WMinecraft;

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
        if (this.side().isServer()) {
            return this.minecraft();
        }
        return this.client();
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
        return WMinecraft.determineSide(this.isClient());
    }

    @Override
    public boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
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
