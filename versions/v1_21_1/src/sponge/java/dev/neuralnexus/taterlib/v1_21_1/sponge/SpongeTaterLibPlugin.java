/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21_1.sponge;

import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeCommandListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_16_5.sponge.listeners.SpongeServerListener;
import dev.neuralnexus.taterlib.v1_19_4.sponge.listeners.SpongePlayerMessageListener;
import dev.neuralnexus.taterlib.v1_20_6.sponge.listeners.SpongePlayerLogoutListener;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.plugin.PluginContainer;

import java.lang.invoke.MethodHandles;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onEnable() {
        TaterLib.start();
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.SPONGE)) {
            // Register listeners
            PluginContainer container = (PluginContainer) Loader.instance().plugin();
            EventManager eventManager = Sponge.eventManager();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            eventManager.registerListeners(container, new SpongeBlockListener(), lookup);
            eventManager.registerListeners(container, new SpongeCommandListener(), lookup);
            eventManager.registerListeners(container, new SpongeEntityListener(), lookup);
            eventManager.registerListeners(container, new SpongePlayerListener(), lookup);
            eventManager.registerListeners(container, new SpongePlayerLogoutListener(), lookup);
            eventManager.registerListeners(container, new SpongePlayerMessageListener(), lookup);
            eventManager.registerListeners(container, new SpongeServerListener(), lookup);
        }
    }
}
