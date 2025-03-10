/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.sponge;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_8_9.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_8_9.sponge.listeners.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_8_9.sponge.listeners.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_8_9.sponge.listeners.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_8_9.sponge.listeners.SpongeServerListener;
import dev.neuralnexus.taterlib.v1_8_9.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPI.instance().setServer(Platforms.SPONGE, SpongeServer::instance);
        if (MetaAPI.instance().version().isOlderThan(MinecraftVersions.V9)) {
            SpongeBootstrap.init();
        } else {
            dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeBootstrap.init();
        }
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
}
