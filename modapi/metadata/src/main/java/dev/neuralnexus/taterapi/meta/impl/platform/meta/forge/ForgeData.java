/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.forge;

import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterapi.meta.Platform;

/** Stores data about the Forge platform */
public final class ForgeData {
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
}
