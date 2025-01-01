/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta.forge;

import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.Platform;

/** Stores data about the Forge platform */
public class ForgeData {
    public static Platform.Meta create() {
        if (checkForClass("net.minecraftforge.fml.loading.FMLLoader")) {
            return new FMLLoaderMeta();
        } else if (checkForClass("net.minecraftforge.fml.common.Loader")) {
            return new MCFLoaderMeta();
        } else if (checkForClass("cpw.mods.fml.common.Loader")) {
            return new CPWLoaderMeta();
        } else {
            return null;
        }
    }

    static Mappings mappings(MinecraftVersion version) {
        if (version.isOlderThan(MinecraftVersions.V16_5)) {
            return Mappings.LEGACY_SEARGE;
        } else if (version.isOlderThan(MinecraftVersions.V20_5)) {
            return Mappings.SEARGE;
        }
        return Mappings.MOJMAP;
    }
}
