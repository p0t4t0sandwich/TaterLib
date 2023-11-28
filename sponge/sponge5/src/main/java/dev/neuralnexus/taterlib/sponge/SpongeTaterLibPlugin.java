package dev.neuralnexus.taterlib.sponge;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterlib.sponge.server.SpongeServer;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;

/** Sponge entry point. */
@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        description = TaterLib.Constants.PROJECT_DESCRIPTION)
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private static SpongeTaterLibPlugin instance;

    @Inject
    public SpongeTaterLibPlugin(Logger logger, PluginContainer container) {
        TaterAPIProvider.register(Sponge.getPlatform().getMinecraftVersion().getName());
        pluginStart(container, new SpongeLogger(logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.SPONGE);
        api.setIsPluginLoaded(Sponge.getPluginManager()::isLoaded);
        api.setServer(() -> new SpongeServer(Sponge.getServer()));

        instance = this;

        // Register listeners
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

    /**
     * Gets the instance of the plugin
     *
     * @return The instance of the plugin
     */
    public static SpongeTaterLibPlugin getInstance() {
        return instance;
    }

    /**
     * Fired when the server stops.
     *
     * @param event The event
     */
    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        pluginStop();
    }
}
