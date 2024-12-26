/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.fabric;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.*;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.api.FabricBlockEvents;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.api.FabricEntityEvents;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.api.FabricPlayerEvents;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.block.FabricBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.entity.FabricEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.entity.FabricEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.event.player.*;
import dev.neuralnexus.taterlib.v1_9_4.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_9_4.fabric.resources.FabricResourceKey;

import net.legacyfabric.fabric.api.command.v2.CommandRegistrar;
import net.legacyfabric.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.legacyfabric.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public class FabricTaterLibPlugin implements TaterLibPlugin {
    private static MinecraftServer server;

    @Override
    public void onInit() {
        TaterAPIProvider.registerBuilder(ResourceKey.Builder.class, FabricResourceKey.Builder::new);
        TaterAPIProvider.registerFactory(ResourceKey.Factory.class, FabricResourceKey.Factory::new);
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        start();
        TaterAPIProvider.api(Platform.FABRIC)
                .ifPresent(api -> api.setServer(() -> (SimpleServer) server));

        if (MetaAPI.instance().isPrimaryPlatform(Platform.FABRIC)) {
            // Initialize plugin data
            ServerLifecycleEvents.SERVER_STARTING.register(s -> server = s);
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> stop());

            // Register Fabric API command events
            CommandRegistrar.EVENT.register(
                    (manager, dedicated) ->
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new FabricCommandRegisterEvent(manager)));

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
                    s -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {}));

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
                            EntityEvents.DEATH.invoke(new FabricEntityDeathEvent(entity)));
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
