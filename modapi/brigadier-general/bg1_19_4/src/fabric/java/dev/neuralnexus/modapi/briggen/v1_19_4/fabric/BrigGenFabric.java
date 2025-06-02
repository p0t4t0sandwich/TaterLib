/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.briggen.v1_19_4.fabric;

import dev.neuralnexus.modapi.briggen.BrigGenPlugin;
import dev.neuralnexus.modapi.briggen.EventHelper;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class BrigGenFabric implements BrigGenPlugin {
    @Override
    public void onInit() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FABRIC)) {
            // TODO: Load if fabric-command-api-v2
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, registryAccess, environment) ->
                            EventHelper.registerCommand(dispatcher));
        }
    }
}
