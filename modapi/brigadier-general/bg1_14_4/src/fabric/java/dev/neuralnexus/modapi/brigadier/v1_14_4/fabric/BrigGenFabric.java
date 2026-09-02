/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.v1_14_4.fabric;

import dev.neuralnexus.modapi.brigadier.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier.EventHelper;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

@AConstraint(
        mappings = Mappings.YARN_INTERMEDIARY,
        platform = Platform.FABRIC,
        version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V18_2))
public class BrigGenFabric implements BrigGenPlugin {
    @Override
    public void onInit() {
        if (MetaAPI.instance().platform().isFabric()) {
            // TODO: Load if fabric-command-api-v1
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, dedicated) -> EventHelper.registerCommand(dispatcher));
        }
    }
}
