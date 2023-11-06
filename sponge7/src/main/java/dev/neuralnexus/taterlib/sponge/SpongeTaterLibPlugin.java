package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.sponge.event.api.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
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
 * The TaterLib Sponge plugin.
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
        TaterAPIProvider.register(new TaterAPI.Data(
                "config",
                Sponge.getPlatform().getMinecraftVersion().getName()
        ));
        pluginStart(container, new SpongeLogger(logger));
        TaterAPI api = TaterAPIProvider.get();

        // Register LuckPerms hook
        if (Sponge.getPluginManager().isLoaded("luckperms")) {
            TaterLib.getLogger().info("LuckPerms detected, enabling LuckPerms hook.");
            api.addHook("luckperms", new LuckPermsHook());
        }

        instance = this;

        // Register commands
        Sponge.getScheduler().createTaskBuilder().delay(10, TimeUnit.SECONDS).execute(() -> CommandEvents.REGISTER_COMMAND.invoke(new SpongeCommandRegisterEvent())).submit(container);

        EventManager eventManager = Sponge.getEventManager();

        // Register entity event listeners
        eventManager.registerListeners(container, new SpongeEntityListener());

        // Register player event listeners
        eventManager.registerListeners(container, new SpongePlayerListener());

        // Register server event listeners
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
