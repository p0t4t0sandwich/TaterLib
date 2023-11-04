package dev.neuralnexus.taterlib.sponge;

import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterlib.sponge.commands.SpongeTaterLibCommand;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;

/**
 * The TaterLib Sponge plugin.
 */
@Plugin(Constants.PROJECT_ID)
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    /**
     * Fired when the server starts.
     * @param event The event
     */
    @Listener
    public void onServerStarting(StartingEngineEvent<Server> event) {
        pluginStart(this, new SpongeLogger(logger));
        TaterLib.configFolder = "config";
        TaterLib.serverType = "Sponge";
        TaterLib.minecraftVersion = Sponge.platform().container(Platform.Component.GAME).metadata().version().toString();

        // Register LuckPerms hook
        if (Sponge.pluginManager().plugin("luckperms").isPresent()) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }

        EventManager eventManager = Sponge.eventManager();

        // Register entity event listeners
        eventManager.registerListeners(this.container, new SpongeEntityListener());

        // Register player event listeners
        eventManager.registerListeners(this.container, new SpongePlayerListener());

        // Register server event listeners
        eventManager.registerListeners(this.container, new SpongeServerListener());
    }

    /**
     * Register commands.
     * @param event The event
     */
    @Listener
    public void onRegisterCommands(final RegisterCommandEvent<Command.Parameterized> event) {
        new SpongeTaterLibCommand().onRegisterCommands(container, event);
    }

    /**
     * Register brigadier commands.
     * @param event The event
     */
    @Listener
    public void onRegisterBrigadierCommands(final RegisterCommandEvent<LiteralCommandNode<?>> event) {
//        event.register()
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
