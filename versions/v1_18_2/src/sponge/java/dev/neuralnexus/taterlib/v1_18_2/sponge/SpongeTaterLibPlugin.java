/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_18_2.sponge;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeCommandListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongePlayerLogoutListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongePlayerMessageListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeServerListener;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.plugin.PluginContainer;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().isPrimaryPlatform(Platforms.SPONGE)) {
            TaterAPI.setLoaded(true);
            // TODO: Init Vanilla methods
            // VanillaBootstrap.init();
        }
    }

    @Override
    public void onEnable() {
        TaterLib.start();
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.SPONGE)) {
            // Register listeners
            PluginContainer container = (PluginContainer) Loader.instance().plugin();
            EventManager eventManager = Sponge.eventManager();
            eventManager.registerListeners(container, new SpongeBlockListener());
            eventManager.registerListeners(container, new SpongeCommandListener());
            eventManager.registerListeners(container, new SpongeEntityListener());
            eventManager.registerListeners(container, new SpongePlayerListener());
            eventManager.registerListeners(container, new SpongePlayerLogoutListener());
            eventManager.registerListeners(container, new SpongePlayerMessageListener());
            eventManager.registerListeners(container, new SpongeServerListener());
        }
    }
}
