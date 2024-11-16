/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.forge;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.PlatformData;
import dev.neuralnexus.modapi.metadata.impl.ModInfoImpl;
import dev.neuralnexus.modapi.metadata.logger.Logger;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the FMLLoader platform */
public class FMLLoaderData implements PlatformData {
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
    public String modLoaderVersion() {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersions.V17)) {
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
                                        modContainer.getVersion().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersions.V18_2)) {
            return ForgeLogger_7_181.logger(pluginId);
        }
        return ForgeLogger_182_21.logger(pluginId);
    }
}
