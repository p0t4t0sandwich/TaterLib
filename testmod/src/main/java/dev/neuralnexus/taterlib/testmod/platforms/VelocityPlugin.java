/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;

import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterlib.testmod.TestMod;

/** Velocity entry point. */
@Plugin(
        id = TestMod.PROJECT_ID,
        name = TestMod.PROJECT_NAME,
        version = TestMod.PROJECT_VERSION,
        authors = TestMod.PROJECT_AUTHORS,
        description = TestMod.PROJECT_DESCRIPTION,
        url = TestMod.PROJECT_URL,
        dependencies = {@Dependency(id = "taterlib")})
public class VelocityPlugin {
    public VelocityPlugin() {
        PluginEvents.ENABLED.register(event -> TestMod.instance().onEnable());
    }
}
