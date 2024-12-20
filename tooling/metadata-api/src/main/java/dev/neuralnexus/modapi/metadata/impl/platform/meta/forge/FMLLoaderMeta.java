/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta.forge;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.logger.ApacheLogger;
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.ModInfoImpl;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LauncherVersion;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the FMLLoader platform */
public class FMLLoaderMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
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
    public String loaderVersion() {
        return LauncherVersion.getVersion();
    }

    @Override
    public String apiVersion() {
        if (minecraftVersion().isOlderThan(MinecraftVersions.V17)) {
            return ForgeVersion_13_16.forgeVersion();
        }
        return ForgeVersion_17_21.forgeVersion();
    }

    @Override
    public Mappings mappings() {
        if (minecraftVersion().isOlderThan(MinecraftVersions.V20_5)) {
            return Mappings.SEARGE;
        }
        return Mappings.MOJMAP;
    }

    @Override
    public List<ModInfo> modList() {
        return ModList.get().getMods().stream()
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
    public Logger<?> logger(String modId) {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersions.V18_2)) {
            return new ApacheLogger(modId);
        }
        return new Slf4jLogger(modId);
    }
}
