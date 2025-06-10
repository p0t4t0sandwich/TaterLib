/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier_general.v1_14_4.forge;

import dev.neuralnexus.modapi.brigadier_general.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier_general.EventHelper;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class BrigGenForge implements BrigGenPlugin {
    @Override
    public void onInit() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FORGE)) {
            MinecraftForge.EVENT_BUS.<FMLServerStartingEvent>addListener(
                    event -> EventHelper.registerCommand(event.getCommandDispatcher()));
        }
    }
}
