/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11.sponge;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.sponge.legacy.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.legacy.listeners.SpongeBlockListener;
import dev.neuralnexus.taterlib.sponge.legacy.listeners.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.legacy.listeners.SpongeServerListener;
import dev.neuralnexus.taterlib.sponge.legacy.server.SpongeServer;
import dev.neuralnexus.taterlib.v1_11.sponge.listeners.SpongePlayerListener;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPI.instance().setServer(Platforms.SPONGE, SpongeServer::instance);
    }

    @Override
    public void onEnable() {
        TaterLib.start();
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.SPONGE)) {
            // Register listeners
            PluginContainer container = (PluginContainer) Loader.instance().plugin();
            EventManager eventManager = Sponge.getEventManager();
            eventManager.registerListeners(container, new SpongeBlockListener());
            Sponge.getScheduler()
                    .createTaskBuilder()
                    .delay(10, TimeUnit.SECONDS)
                    .execute(
                            () ->
                                    CommandEvents.REGISTER_COMMAND.invoke(
                                            new SpongeCommandRegisterEvent()))
                    .submit(container);
            eventManager.registerListeners(container, new SpongeEntityListener());
            eventManager.registerListeners(container, new SpongePlayerListener());
            eventManager.registerListeners(container, new SpongeServerListener());
        }
    }

    @Override
    public void onDisable() {
        TaterLib.stop();
    }
}
