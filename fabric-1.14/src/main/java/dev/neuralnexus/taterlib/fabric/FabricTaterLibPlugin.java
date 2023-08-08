package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.listeners.player.CommonPlayerListener;
import dev.neuralnexus.taterlib.fabric.abstractions.logger.FabricLogger;
import dev.neuralnexus.taterlib.fabric.commands.FabricTaterLibCommand;
import dev.neuralnexus.taterlib.fabric.abstractions.player.FabricPlayer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;

/**
 * The TaterLib Fabric plugin.
 */
public class FabricTaterLibPlugin extends TemplateFabricPlugin implements TaterLibPlugin {
    private static final String MOD_ID = "taterlib";

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new FabricLogger( "[TaterLib] ", LogManager.getLogger(MOD_ID));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            if (FabricLoader.getInstance().isModLoaded("luckperms")) {
                useLogger("[TaterAPI] LuckPerms detected, enabling LuckPerms hook.");
                TaterLib.addHook(new LuckPermsHook());
            }
        });
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        // Register Fabric API player events
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> CommonPlayerListener.onPlayerLogin(new FabricPlayer(handler.player)));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> CommonPlayerListener.onPlayerLogout(new FabricPlayer(handler.player)));

        // Register Fabric API server events
        ServerLifecycleEvents.SERVER_STARTING.register(server -> FabricTaterLibPlugin.server = server);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        CommandRegistrationCallback.EVENT.register(FabricTaterLibCommand::register);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onInitializeServer() {
        pluginStart();
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());
    }
}
