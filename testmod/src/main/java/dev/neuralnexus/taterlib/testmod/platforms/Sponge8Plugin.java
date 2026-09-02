/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterlib.testmod.TestMod;

import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TestMod.PROJECT_ID)
public class Sponge8Plugin {
    public Sponge8Plugin() {
        PluginEvents.ENABLED.register(event -> TestMod.instance().onEnable());
    }
}
