/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier_general.v1_14_4.fabric;

import dev.neuralnexus.modapi.brigadier_general.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier_general.EventHelper;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class BrigGenFabric implements BrigGenPlugin {
    @Override
    public void onInit() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FABRIC)) {
            // TODO: Load if fabric-command-api-v1
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, dedicated) -> EventHelper.registerCommand(dispatcher));
        }
    }
}
