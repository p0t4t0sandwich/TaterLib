package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.*;
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

import java.util.stream.Collectors;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    public static MinecraftServer minecraftServer;

    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        pluginStart(
                plugin,
                server,
                logger,
                new LoggerAdapter(
                        "[" + TaterLib.Constants.PROJECT_NAME + "] ",
                        TaterLib.Constants.PROJECT_ID,
                        LogManager.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPI api = TaterAPIProvider.get(ServerType.FABRIC);
        api.setModList(
                () ->
                        FabricLoader.getInstance().getAllMods().stream()
                                .map(
                                        modContainer ->
                                                new ModInfo(
                                                        modContainer.getMetadata().getId(),
                                                        modContainer.getMetadata().getName(),
                                                        modContainer
                                                                .getMetadata()
                                                                .getVersion()
                                                                .getFriendlyString()))
                                .collect(Collectors.toList()));
        api.setServer(() -> new FabricServer(minecraftServer));
        TaterAPIProvider.setPrimaryServerType(ServerType.FABRIC);

        if (TaterAPIProvider.isPrimaryServerType(ServerType.FABRIC)) {
            // Initialize plugin data
            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> FabricTaterLibPlugin.minecraftServer = s);
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> pluginStop());

            // Register Fabric API command events
            CommandRegistrar.EVENT.register(
                    (manager, dedicated) ->
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new FabricCommandRegisterEvent(manager, dedicated)));

            // Register Fabric API player events
            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, s) ->
                            PlayerEvents.LOGIN.invoke(
                                    new FabricPlayerLoginEvent(handler, sender, s)));
            ServerPlayConnectionEvents.DISCONNECT.register(
                    (handler, s) ->
                            PlayerEvents.LOGOUT.invoke(new FabricPlayerLogoutEvent(handler, s)));

            // Register Fabric API server events
            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> ServerEvents.STARTING.invoke(new FabricServerStartingEvent(s)));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new FabricServerStartedEvent(s)));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new FabricServerStoppingEvent(s)));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new FabricServerStoppedEvent(s)));

            // Register TaterLib Block events
            FabricBlockEvents.BLOCK_BREAK.register(
                    (world, player, pos, state, blockEntity, stack, ci) ->
                            BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                                    new FabricBlockBreakEvent(
                                            world, player, pos, state, blockEntity, stack, ci)));

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
    }
}
