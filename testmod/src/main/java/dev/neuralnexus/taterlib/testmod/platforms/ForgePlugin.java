/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

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
