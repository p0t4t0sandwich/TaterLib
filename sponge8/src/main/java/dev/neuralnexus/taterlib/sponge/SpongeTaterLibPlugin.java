package dev.neuralnexus.taterlib.sponge;

import com.google.inject.Inject;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.sponge.listeners.command.SpongeCommandListener;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/**
 * The TaterLib Sponge plugin.
 */
@Plugin(Constants.PROJECT_ID)
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Inject
    public SpongeTaterLibPlugin(Logger logger, PluginContainer container) {
        TaterLib.configFolder = "config";
        TaterLib.serverType = "Sponge";
        TaterLib.minecraftVersion = Sponge.platform().container(Platform.Component.GAME).metadata().version().toString();
        pluginStart(container, new SpongeLogger(logger));

        // Register LuckPerms hook
        if (Sponge.pluginManager().plugin("luckperms").isPresent()) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }

        EventManager eventManager = Sponge.eventManager();

        // Register command events
        eventManager.registerListeners(container, new SpongeCommandListener());

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
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        pluginStop();
    }
}
