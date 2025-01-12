/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9.sponge;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_9.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.v1_9.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        start();
        TaterAPIProvider.api(Platforms.SPONGE)
                .ifPresent(api -> api.setServer(() -> new SpongeServer(Sponge.getServer())));
    }

    @Override
    public void onEnable() {
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
        stop();
    }
}
