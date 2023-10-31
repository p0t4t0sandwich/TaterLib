package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
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

@Plugin(id = "taterlib", name = "TaterLib", version = "1.0.2-SNAPSHOT", description = "A cross API code library for various generalizations used in the Tater* plugins")
public class SpongeTaterLibPlugin extends TemplateSpongePlugin implements TaterLibPlugin {
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
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new SpongeLogger(logger);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        if (Sponge.getPluginManager().isLoaded("luckperms")) {
            useLogger("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook(new LuckPermsHook());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        EventManager eventManager = Sponge.getEventManager();

        // Register entity event listeners
        eventManager.registerListeners(this.container, new SpongeEntityListener());

        // Register player event listeners
        eventManager.registerListeners(this.container, new SpongePlayerListener());

        // Register server event listeners
        eventManager.registerListeners(this.container, new SpongeServerListener());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        new SpongeTaterLibCommand().onRegisterCommands(container);
    }

    /**
     * Fired when the server starts.
     * @param event The event
     */
    @Listener
    public void onServerStarting(GameStartedServerEvent event) {
        instance = this;
        pluginStart();
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
