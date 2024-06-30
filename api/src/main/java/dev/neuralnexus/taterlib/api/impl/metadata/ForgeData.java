/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api.impl.metadata;

import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.PlatformData;
import dev.neuralnexus.taterlib.api.impl.metadata.forge.CPWLoaderData;
import dev.neuralnexus.taterlib.api.impl.metadata.forge.FMLLoaderData;
import dev.neuralnexus.taterlib.api.impl.metadata.forge.MCFLoaderData;
import dev.neuralnexus.taterlib.logger.Logger;

import java.util.List;

/** Stores data about the Fabric platform */
public class ForgeData implements PlatformData {
    private final PlatformData forgePlatformData;

    public ForgeData() {
        if (checkForClass("net.minecraftforge.fml.loading.FMLLoader")) {
            forgePlatformData = new FMLLoaderData();
        } else if (checkForClass("net.minecraftforge.fml.common.Loader")) {
            forgePlatformData = new MCFLoaderData();
        } else if (checkForClass("cpw.mods.fml.common.Loader")) {
            forgePlatformData = new CPWLoaderData();
        } else {
            forgePlatformData = null;
        }
    }

    @Override
    public MinecraftVersion minecraftVersion() {
        return forgePlatformData.minecraftVersion();
    }

    @Override
    public String modLoaderVersion() {
        return forgePlatformData.modLoaderVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return forgePlatformData.modList();
    }

    @Override
    public Logger logger(String pluginId) {
        return forgePlatformData.logger(pluginId);
    }
}
