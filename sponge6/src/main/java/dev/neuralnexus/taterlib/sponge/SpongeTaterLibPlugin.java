package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterlib.sponge.commands.SpongeTaterLibCommand;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import com.google.inject.Inject;
import org.slf4j.Logger;

/**
 * The TaterLib Sponge plugin.
 */
@Plugin(
        id = Constants.PROJECT_ID,
        name = Constants.PROJECT_NAME,
        version = Constants.PROJECT_VERSION,
        description = Constants.PROJECT_DESCRIPTION
)
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    private static SpongeTaterLibPlugin instance;

    /**
     * Gets the instance of the plugin
     * @return The instance of the plugin
     */
    public static SpongeTaterLibPlugin getInstance() {
        return instance;
    }

    /**
     * Fired when the server starts.
     * @param event The event
     */
    @Listener
    public void onServerStarting(GameStartedServerEvent event) {
        instance = this;
        pluginStart(this, new SpongeLogger(logger));
        TaterLib.configFolder = "config";
        TaterLib.serverType = "Sponge";
        TaterLib.minecraftVersion = Sponge.getPlatform().getMinecraftVersion().getName();

        // Register LuckPerms hook
        if (Sponge.getPluginManager().isLoaded("luckperms")) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());

            // Register commands
            new SpongeTaterLibCommand().onRegisterCommands(container);

            EventManager eventManager = Sponge.getEventManager();

            // Register entity event listeners
            eventManager.registerListeners(this.container, new SpongeEntityListener());

            // Register player event listeners
            eventManager.registerListeners(this.container, new SpongePlayerListener());

            // Register server event listeners
            eventManager.registerListeners(this.container, new SpongeServerListener());
        }
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
