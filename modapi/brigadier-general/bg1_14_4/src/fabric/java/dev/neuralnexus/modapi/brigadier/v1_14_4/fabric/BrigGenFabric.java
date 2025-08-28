/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.v1_14_4.fabric;

import dev.neuralnexus.modapi.brigadier.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier.EventHelper;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class BrigGenFabric implements BrigGenPlugin {
    @Override
    public Mappings mappings() {
        return Mappings.YARN_INTERMEDIARY;
    }

    @Override
    public Platform platform() {
        return Platforms.FABRIC;
    }

    @Override
    public MinecraftVersion min() {
        return MinecraftVersions.V14;
    }

    @Override
    public MinecraftVersion max() {
        return MinecraftVersions.V18_2;
    }

    @Override
    public void onInit() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FABRIC)) {
            // TODO: Load if fabric-command-api-v1
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, dedicated) -> EventHelper.registerCommand(dispatcher));
        }
    }
}
