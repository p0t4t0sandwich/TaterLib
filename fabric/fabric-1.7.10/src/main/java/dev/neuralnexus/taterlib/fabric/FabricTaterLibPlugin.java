package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.*;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.fabric.event.api.FabricBlockEvents;
import dev.neuralnexus.taterlib.fabric.event.api.FabricEntityEvents;
import dev.neuralnexus.taterlib.fabric.event.api.FabricPlayerEvents;
import dev.neuralnexus.taterlib.fabric.event.block.FabricBlockBreakEvent;
import dev.neuralnexus.taterlib.fabric.event.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.fabric.event.entity.FabricEntityDamageEvent;
import dev.neuralnexus.taterlib.fabric.event.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.fabric.event.entity.FabricEntitySpawnEvent;
import dev.neuralnexus.taterlib.fabric.event.player.*;
import dev.neuralnexus.taterlib.fabric.event.server.FabricServerStartedEvent;
import dev.neuralnexus.taterlib.fabric.event.server.FabricServerStartingEvent;
import dev.neuralnexus.taterlib.fabric.event.server.FabricServerStoppedEvent;
import dev.neuralnexus.taterlib.fabric.event.server.FabricServerStoppingEvent;
import dev.neuralnexus.taterlib.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import net.fabricmc.loader.api.FabricLoader;
import net.legacyfabric.fabric.api.command.v2.CommandRegistrar;
import net.legacyfabric.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.legacyfabric.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.LogManager;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    public static MinecraftServer server;

    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.register();
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        pluginStart(
                plugin,
                new LoggerAdapter(
                        "[" + TaterLib.Constants.PROJECT_NAME + "] ",
                        TaterLib.Constants.PROJECT_ID,
                        LogManager.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPI api = TaterAPIProvider.get(ServerType.FABRIC);
        api.setIsModLoaded((modId) -> FabricLoader.getInstance().isModLoaded(modId));
        api.setServer(() -> new FabricServer(server));

        // Initialize plugin data
        ServerLifecycleEvents.SERVER_STARTING.register(
                server -> FabricTaterLibPlugin.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());

        // Register Fabric API command events
        CommandRegistrar.EVENT.register(
                (manager, dedicated) ->
                        CommandEvents.REGISTER_COMMAND.invoke(
                                new FabricCommandRegisterEvent(manager, dedicated)));

        // Register Fabric API player events
        ServerPlayConnectionEvents.JOIN.register(
                (handler, sender, server) ->
                        PlayerEvents.LOGIN.invoke(
                                new FabricPlayerLoginEvent(handler, sender, server)));
        ServerPlayConnectionEvents.DISCONNECT.register(
                (handler, server) ->
                        PlayerEvents.LOGOUT.invoke(new FabricPlayerLogoutEvent(handler, server)));

        // Register Fabric API server events
        ServerLifecycleEvents.SERVER_STARTING.register(
                server -> ServerEvents.STARTING.invoke(new FabricServerStartingEvent(server)));
        ServerLifecycleEvents.SERVER_STARTED.register(
                server -> ServerEvents.STARTED.invoke(new FabricServerStartedEvent(server)));
        ServerLifecycleEvents.SERVER_STOPPING.register(
                server -> ServerEvents.STOPPING.invoke(new FabricServerStoppingEvent(server)));
        ServerLifecycleEvents.SERVER_STOPPED.register(
                server -> ServerEvents.STOPPED.invoke(new FabricServerStoppedEvent(server)));

        // Register TaterLib Block events
        FabricBlockEvents.BLOCK_BREAK.register(
                (world, player, pos, state, blockEntity, ci) ->
                        BlockEvents.BLOCK_BREAK.invoke(
                                new FabricBlockBreakEvent(
                                        world, player, pos, state, blockEntity, ci)));

        // Register TaterLib Entity events
        FabricEntityEvents.DAMAGE.register(
                (entity, damageSource, damage, ci) ->
                        EntityEvents.DAMAGE.invoke(
                                new FabricEntityDamageEvent(entity, damageSource, damage, ci)));
        FabricEntityEvents.DEATH.register(
                (entity, source) ->
                        EntityEvents.DEATH.invoke(new FabricEntityDeathEvent(entity, source)));
        FabricEntityEvents.SPAWN.register(
                (entity, cir) ->
                        EntityEvents.SPAWN.invoke(new FabricEntitySpawnEvent(entity, cir)));

        // Register TaterLib Player events
        FabricPlayerEvents.ADVANCEMENT_FINISHED.register(
                (player, advancement) ->
                        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                                new FabricPlayerAdvancementEvent.AdvancementFinished(
                                        player, advancement)));
        FabricPlayerEvents.ADVANCEMENT_PROGRESS.register(
                (player, advancement, criterionName) ->
                        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                                new FabricPlayerAdvancementEvent.AdvancementProgress(
                                        player, advancement, criterionName)));
        FabricPlayerEvents.DEATH.register(
                (player, source) ->
                        PlayerEvents.DEATH.invoke(new FabricPlayerDeathEvent(player, source)));
        FabricPlayerEvents.MESSAGE.register(
                (player, message, ci) ->
                        PlayerEvents.MESSAGE.invoke(
                                new FabricPlayerMessageEvent(player, message, ci)));
        FabricPlayerEvents.RESPAWN.register(
                ((player, dimension, alive) ->
                        PlayerEvents.RESPAWN.invoke(
                                new FabricPlayerRespawnEvent(player, dimension, alive))));
    }

    @Override
    public void platformEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
    }
}
