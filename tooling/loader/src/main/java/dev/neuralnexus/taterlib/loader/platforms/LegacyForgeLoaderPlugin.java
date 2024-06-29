/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForClass;

import cpw.mods.fml.common.Mod;

import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.TaterPluginResolver;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

/** Legacy Forge entry point. */
@Mod(
        modid = LoaderImpl.PROJECT_ID,
        name = LoaderImpl.PROJECT_NAME,
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = LoaderImpl.PROJECT_NAME)
public class LegacyForgeLoaderPlugin {
    private static Loader loader;

    public LegacyForgeLoaderPlugin() {
        TaterAPIProvider.setPrimaryPlatform(Platform.FORGE);
        loader = new LoaderImpl(this, null, null);
        loader.registerPlugin(TaterPluginResolver.legacyForge(loader));
        if (loader.platform().isForgeHybrid() && checkForClass("org.bukkit.Bukkit")) {
            loader.registerPlugin(TaterPluginResolver.bukkit(loader));
        }
        loader.onInit();
        loader.onEnable();
    }
}
