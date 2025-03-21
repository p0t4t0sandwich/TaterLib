/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterlib.testmod.TestMod;

import org.spongepowered.api.plugin.Plugin;

/** Sponge entry point. */
@Plugin(
        id = TestMod.PROJECT_ID,
        name = TestMod.PROJECT_NAME,
        version = TestMod.PROJECT_VERSION,
        description = TestMod.PROJECT_DESCRIPTION)
public class Sponge7Plugin {
    public Sponge7Plugin() {
        PluginEvents.ENABLED.register(event -> TestMod.instance().onEnable());
    }
}
