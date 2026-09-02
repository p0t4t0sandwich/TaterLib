/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
