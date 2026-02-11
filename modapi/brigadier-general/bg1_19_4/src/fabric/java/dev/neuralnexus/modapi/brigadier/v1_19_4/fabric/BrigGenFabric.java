/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.v1_19_4.fabric;

import dev.neuralnexus.modapi.brigadier.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier.EventHelper;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

@AConstraint(
        mappings = Mappings.YARN_INTERMEDIARY,
        platform = Platform.FABRIC,
        version = @Versions(min = MinecraftVersion.V19))
public class BrigGenFabric implements BrigGenPlugin {
    @Override
    public void onInit() {
        if (MetaAPI.instance().platform().isFabric()) {
            // TODO: Load if fabric-command-api-v2
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, registryAccess, environment) ->
                            EventHelper.registerCommand(dispatcher));
        }
    }
}
