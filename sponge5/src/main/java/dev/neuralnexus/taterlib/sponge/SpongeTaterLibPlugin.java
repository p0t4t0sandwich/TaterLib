package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.PluginEvents;
import dev.neuralnexus.taterlib.common.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.sponge.server.SpongeServer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import com.google.inject.Inject;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Sponge entry point.
 */
@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        description = TaterLib.Constants.PROJECT_DESCRIPTION
)
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private static SpongeTaterLibPlugin instance;

    /**
     * Gets the instance of the plugin
     * @return The instance of the plugin
     */
    public static SpongeTaterLibPlugin getInstance() {
        return instance;
    }

    @Inject
    public SpongeTaterLibPlugin(Logger logger, PluginContainer container) {
        TaterAPIProvider.register("config", Sponge.getPlatform().getMinecraftVersion().getName());
        pluginStart(container, new SpongeLogger(logger));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded(Sponge.getPluginManager()::isLoaded);
        api.setServer(() -> new SpongeServer(Sponge.getServer()));

        instance = this;

        // Register listeners
        EventManager eventManager = Sponge.getEventManager();
        eventManager.registerListeners(container, new SpongeBlockListener());
        Sponge.getScheduler().createTaskBuilder().delay(10, TimeUnit.SECONDS).execute(() -> CommandEvents.REGISTER_COMMAND.invoke(new SpongeCommandRegisterEvent())).submit(container);
        eventManager.registerListeners(container, new SpongeEntityListener());
        eventManager.registerListeners(container, new SpongePlayerListener());
        eventManager.registerListeners(container, new SpongeServerListener());
    }

    /**
     * Fired when the server stops.
     * @param event The event
     */
    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        pluginStop();
    }
}
