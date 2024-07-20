/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metadata.impl.forge;

import net.minecraftforge.fml.loading.FMLLoader;

public class ForgeVersion_17_21 {
    public static String forgeVersion() {
        return FMLLoader.versionInfo().forgeVersion();
    }
}
