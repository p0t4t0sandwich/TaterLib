/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.forge;

import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForClass;

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
import dev.neuralnexus.taterapi.meta.impl.platform.meta.ModInfoImpl;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LauncherVersion;
import net.minecraftforge.fml.loading.LoadingModList;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the FMLLoader platform */
final class FMLLoaderMeta implements Platform.Meta {
    private final boolean oldLifeCycleHooks =
            checkForClass("net.minecraftforge.fml.server.ServerLifecycleHooks");

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
    public @NotNull Object minecraft() {
        if (this.side().isClient() && WMinecraft.hasServer()) {
            return WMinecraft.getServer();
        }
        if (this.oldLifeCycleHooks) {
            return net.minecraftforge.fml.server.ServerLifecycleHooks.getCurrentServer();
        }
        return net.minecraftforge.server.ServerLifecycleHooks.getCurrentServer();
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
        String version = "Unknown";
        try {
            try {
                // Reflect to get FMLLoader.versionInfo().mcVersion()
                Object versionInfoObject = FMLLoader.class.getMethod("versionInfo").invoke(null);
                version =
                        (String)
                                versionInfoObject
                                        .getClass()
                                        .getMethod("mcVersion")
                                        .invoke(versionInfoObject);
            } catch (ReflectiveOperationException e) {
                // Reflect to get private FMLLoader.mcVersion
                Field mcVersionField = FMLLoader.class.getDeclaredField("mcVersion");
                mcVersionField.setAccessible(true);
                version = (String) mcVersionField.get(null);
            }
        } catch (ReflectiveOperationException ignored) {
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public @NotNull String loaderVersion() {
        return LauncherVersion.getVersion();
    }

    @Override
    public @NotNull String apiVersion() {
        if (minecraftVersion().isOlderThan(MinecraftVersions.V17)) {
            return ForgeVersion_13_16.forgeVersion();
        }
        return ForgeVersion_17_21.forgeVersion();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        List<net.minecraftforge.fml.loading.moddiscovery.ModInfo> mods = ModList.get().getMods();
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
                                        Platforms.FORGE))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersions.V18_2)) {
            return new ApacheLogger(modId);
        }
        return new Slf4jLogger(modId);
    }
}
