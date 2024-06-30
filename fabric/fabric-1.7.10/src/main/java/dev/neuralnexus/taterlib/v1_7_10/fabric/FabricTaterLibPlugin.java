/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric;

import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.*;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.api.FabricBlockEvents;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.api.FabricEntityEvents;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.api.FabricPlayerEvents;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.block.FabricBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.entity.FabricEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.entity.FabricEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.player.*;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.server.FabricServerStartedEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.server.FabricServerStartingEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.server.FabricServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.server.FabricServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_7_10.fabric.server.FabricServer;

import net.legacyfabric.fabric.api.command.v2.CommandRegistrar;
import net.legacyfabric.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.legacyfabric.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    public static MinecraftServer minecraftServer;

    @Override
    public void onInit(Object plugin, Object server) {
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        start(plugin, server);
        TaterAPIProvider.api(Platform.FABRIC)
                .ifPresent(api -> api.setServer(() -> new FabricServer(minecraftServer)));

        if (TaterAPIProvider.isPrimaryPlatform(Platform.FABRIC)) {
            // Initialize plugin data
            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> FabricTaterLibPlugin.minecraftServer = s);
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> stop());

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
                    (world, player, pos, state, blockEntity, ci) ->
                            BlockEvents.PLAYER_BLOCK_BREAK.invoke(
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
    }
}
