/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api.impl.metadata;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.PlatformData;
import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericLogger;
import dev.neuralnexus.taterlib.utils.MixinServiceUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/** Stores information about the vanilla platform */
public class VanillaData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            version = MixinServiceUtil.mcVersion();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        return MinecraftVersion.from(version);
    }

    @Override
    public String modLoaderVersion() {
        return "Vanilla";
    }

    @Override
    public List<ModInfo> modList() {
        return Collections.emptyList();
    }

    @Override
    public Logger logger(String pluginId) {
        return new GenericLogger(pluginId);
    }
}