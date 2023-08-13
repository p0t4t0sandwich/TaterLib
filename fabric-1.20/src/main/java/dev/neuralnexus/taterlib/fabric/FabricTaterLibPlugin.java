package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.fabric.abstractions.entity.FabricEntity;
import dev.neuralnexus.taterlib.fabric.abstractions.logger.FabricLogger;
import dev.neuralnexus.taterlib.fabric.abstractions.player.FabricPlayer;
import dev.neuralnexus.taterlib.fabric.commands.FabricTaterLibCommand;
import dev.neuralnexus.taterlib.fabric.events.entity.FabricEntityEvents;
import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.LoggerFactory;

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
        return new FabricLogger( "[TaterLib] ",LoggerFactory.getLogger(MOD_ID));
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
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> PlayerListener.onPlayerLogin(new FabricPlayer(handler.player)));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> PlayerListener.onPlayerLogout(new FabricPlayer(handler.player)));

        // Register Fabric API server events
        ServerLifecycleEvents.SERVER_STARTING.register(server -> FabricTaterLibPlugin.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());

        // Register TaterLib Entity events
        FabricEntityEvents.DEATH.register((entity, source) -> EntityListener.onEntityDeath(new FabricEntity(entity), source.getDeathMessage(entity).getString()));
        FabricEntityEvents.SPAWN.register((entity) -> EntityListener.onEntitySpawn(new FabricEntity(entity)));

        // Register TaterLib Player events
        FabricPlayerEvents.ADVANCEMENT_FINISHED.register((player, advancement) -> PlayerListener.onPlayerAdvancementFinished(new FabricPlayer(player), advancement.getDisplay().getTitle().getString()));
        FabricPlayerEvents.ADVANCEMENT.register((player, advancement) -> PlayerListener.onPlayerAdvancement(new FabricPlayer(player), advancement.getRoot().getDisplay().getTitle().getString()));
        FabricPlayerEvents.DEATH.register((player, source) -> PlayerListener.onPlayerDeath(new FabricPlayer(player), source.getDeathMessage(player).getString()));
        FabricPlayerEvents.MESSAGE.register((player, message, isCanceled) -> PlayerListener.onPlayerMessage(new FabricPlayer(player), message, isCanceled));
        FabricPlayerEvents.RESPAWN.register((player) -> PlayerListener.onPlayerRespawn(new FabricPlayer(player)));
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
    }
}
