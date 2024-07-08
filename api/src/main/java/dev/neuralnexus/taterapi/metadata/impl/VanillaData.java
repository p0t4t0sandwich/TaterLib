/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.metadata.impl;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.SystemLogger;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.PlatformData;
import dev.neuralnexus.taterapi.util.MixinServiceUtil;

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
        return new SystemLogger(pluginId);
    }
}
