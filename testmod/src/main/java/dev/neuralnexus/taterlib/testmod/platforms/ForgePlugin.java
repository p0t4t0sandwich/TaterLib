/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterlib.testmod.TestMod;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = TestMod.PROJECT_ID,
        modid = TestMod.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgePlugin {
    public ForgePlugin() {
        PluginEvents.ENABLED.register(event -> TestMod.instance().onEnable());
    }
}
