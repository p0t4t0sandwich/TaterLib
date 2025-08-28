/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.v1_14_4.forge;

import dev.neuralnexus.modapi.brigadier.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier.EventHelper;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class BrigGenForge implements BrigGenPlugin {
    @Override
    public Mappings mappings() {
        return Mappings.LEGACY_SEARGE;
    }

    @Override
    public Platform platform() {
        return Platforms.FORGE;
    }

    @Override
    public MinecraftVersion min() {
        return MinecraftVersions.V14;
    }

    @Override
    public MinecraftVersion max() {
        return MinecraftVersions.V15_2;
    }

    @Override
    public void onInit() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FORGE)) {
            MinecraftForge.EVENT_BUS.<FMLServerStartingEvent>addListener(
                    event -> EventHelper.registerCommand(event.getCommandDispatcher()));
        }
    }
}
