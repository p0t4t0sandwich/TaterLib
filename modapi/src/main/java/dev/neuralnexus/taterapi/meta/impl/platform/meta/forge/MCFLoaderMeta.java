/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.forge;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.ApacheLogger;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.WMinecraft;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.ModInfoImpl;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the MCF Loader platform */
final class MCFLoaderMeta implements Platform.Meta {
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
        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }

    @Override
    public @NotNull Side side() {
        return WMinecraft.determineSide(FMLCommonHandler.instance().getSide().isClient());
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            // Reflect to get net.minecraftforge.fml.common.Loader.MC_VERSION
            version = (String) Loader.class.getField("MC_VERSION").get(null);
        } catch (ReflectiveOperationException ignored) {
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public @NotNull String loaderVersion() {
        return ForgeVersion_7_12.forgeVersion();
    }

    @Override
    public @NotNull String apiVersion() {
        return ForgeVersion_7_12.forgeVersion();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        return Loader.instance().getModList().stream()
                .map(
                        modContainer ->
                                new ModInfoImpl(
                                        modContainer.getModId(),
                                        modContainer.getName(),
                                        modContainer.getVersion(),
                                        Platforms.FORGE))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        return new ApacheLogger(modId);
    }
}
