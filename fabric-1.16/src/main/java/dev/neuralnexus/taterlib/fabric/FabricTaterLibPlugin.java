package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import dev.neuralnexus.taterlib.fabric.abstractions.events.entity.FabricEntityDamageEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.events.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.events.entity.FabricEntitySpawnEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.events.player.*;
import dev.neuralnexus.taterlib.fabric.abstractions.events.server.FabricServerStartedEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.events.server.FabricServerStartingEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.events.server.FabricServerStoppedEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.events.server.FabricServerStoppingEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.logger.FabricLogger;
import dev.neuralnexus.taterlib.fabric.commands.FabricTaterLibCommand;
import dev.neuralnexus.taterlib.fabric.events.entity.FabricEntityEvents;
import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
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
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> PlayerListener.onPlayerLogin(new FabricPlayerLoginEvent(handler, sender, server)));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> PlayerListener.onPlayerLogout(new FabricPlayerLogoutEvent(handler, server)));

        // Register Fabric API server events
        ServerLifecycleEvents.SERVER_STARTING.register(server -> ServerListener.onServerStarting(new FabricServerStartingEvent()));
        ServerLifecycleEvents.SERVER_STARTED.register(server -> ServerListener.onServerStarted(new FabricServerStartedEvent()));
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> ServerListener.onServerStopping(new FabricServerStoppingEvent()));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> ServerListener.onServerStopped(new FabricServerStoppedEvent()));

        // Register TaterLib Entity events
        FabricEntityEvents.DAMAGE.register((entity, damageSource, damage, ci)  -> EntityListener.onEntityDamage(new FabricEntityDamageEvent(entity, damageSource, damage, ci)));
        FabricEntityEvents.DEATH.register((entity, source) -> EntityListener.onEntityDeath(new FabricEntityDeathEvent(entity, source)));
        FabricEntityEvents.SPAWN.register((entity, cir) -> EntityListener.onEntitySpawn(new FabricEntitySpawnEvent(entity, cir)));

        // Register TaterLib Player events
        FabricPlayerEvents.ADVANCEMENT_FINISHED.register((player, advancement) -> PlayerListener.onPlayerAdvancementFinished(new FabricPlayerAdvancementEvent.FabricPlayerAdvancementFinishedEvent(player, advancement)));
        FabricPlayerEvents.ADVANCEMENT_PROGRESS.register((player, advancement, criterionName) -> PlayerListener.onPlayerAdvancementProgress(new FabricPlayerAdvancementEvent.FabricPlayerAdvancementProgressEvent(player, advancement, criterionName)));
        FabricPlayerEvents.DEATH.register((player, source) -> PlayerListener.onPlayerDeath(new FabricPlayerDeathEvent(player, source)));
        FabricPlayerEvents.MESSAGE.register((player, message, ci) -> PlayerListener.onPlayerMessage(new FabricPlayerMessageEvent(player, message, ci)));
        FabricPlayerEvents.RESPAWN.register(((player, alive) -> PlayerListener.onPlayerRespawn(new FabricPlayerRespawnEvent(player, alive))));
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
        // Initialize plugin data
        ServerLifecycleEvents.SERVER_STARTING.register(server -> FabricTaterLibPlugin.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());
        pluginStart();
    }
}
