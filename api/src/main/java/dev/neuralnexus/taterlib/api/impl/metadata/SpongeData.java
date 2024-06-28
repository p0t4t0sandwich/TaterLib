/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.api.impl.metadata;

import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForMethod;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.PlatformData;

import java.util.List;

/** Stores data about the Fabric platform */
public class SpongeData implements PlatformData {
    private final PlatformData spongePlatformData;

    public SpongeData() {
        if (checkForMethod("org.spongepowered.api.Sponge", "platform")) {
            spongePlatformData = new SpongeModernData();
        } else if (checkForMethod("org.spongepowered.api.Sponge", "getPlatform")) {
            spongePlatformData = new SpongeLegacyData();
        } else {
            spongePlatformData = null;
        }
    }

    @Override
    public MinecraftVersion minecraftVersion() {
        return spongePlatformData.minecraftVersion();
    }

    @Override
    public String modLoaderVersion() {
        return spongePlatformData.modLoaderVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return spongePlatformData.modList();
    }
}
