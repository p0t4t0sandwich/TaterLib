/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.impl.logger.SystemLogger;
import dev.neuralnexus.modapi.metadata.impl.util.MixinServiceUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/** Stores information about the vanilla platform */
public final class VanillaMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            version = MixinServiceUtil.mcVersion();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public String loaderVersion() {
        return minecraftVersion().asString();
    }

    @Override
    public String apiVersion() {
        return minecraftVersion().asString();
    }

    @Override
    public Mappings mappings() {
        return Mappings.MOJMAP;
    }

    @Override
    public List<ModInfo> modList() {
        return Collections.emptyList();
    }

    @Override
    public Logger<?> logger(String modId) {
        // TODO: Do some version parsing and grab the vanilla logger factory
        return new SystemLogger(modId);
    }
}
