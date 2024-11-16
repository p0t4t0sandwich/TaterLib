/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.forge;

import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.modapi.metadata.PlatformData;

/** Stores data about the Forge platform */
public class ForgeData {
    public static PlatformData create() {
        if (checkForClass("net.minecraftforge.fml.loading.FMLLoader")) {
            return new FMLLoaderData();
        } else if (checkForClass("net.minecraftforge.fml.common.Loader")) {
            return new MCFLoaderData();
        } else if (checkForClass("cpw.mods.fml.common.Loader")) {
            return new CPWLoaderData();
        } else {
            return null;
        }
    }
}
