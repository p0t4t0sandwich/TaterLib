/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterlib.testmod.TestMod;

import net.neoforged.fml.common.Mod;

/** NeoForge entry point. */
@Mod(TestMod.PROJECT_ID)
public class NeoForgePlugin {
    public NeoForgePlugin() {
        PluginEvents.ENABLED.register(event -> TestMod.instance().onEnable());
    }
}
