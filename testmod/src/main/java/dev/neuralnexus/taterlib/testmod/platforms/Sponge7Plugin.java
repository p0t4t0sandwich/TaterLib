/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import dev.neuralnexus.taterlib.testmod.TestMod;
import dev.neuralnexus.taterapi.event.api.PluginEvents;

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
