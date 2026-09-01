/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_18_2.sponge;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
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

@AConstraint(
        platform = Platform.SPONGE,
        version = @Versions(min = MinecraftVersion.V17, max = MinecraftVersion.V18_2))
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().platform().isSponge()) {
            TaterAPI.setLoaded(true);
            // TODO: Init Vanilla methods
            // VanillaBootstrap.init();
        }
    }

    @Override
    public void onEnable() {
        TaterLibPlugin.super.onEnable();
        if (MetaAPI.instance().platform().isSponge()) {
            // Register listeners
            PluginContainer container = (PluginContainer) TaterLib.mod();
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
