/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.fabric;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.*;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.api.FabricBlockEvents;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.api.FabricEntityEvents;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.api.FabricPlayerEvents;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.block.FabricBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.entity.FabricEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.entity.FabricEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_10_2.fabric.event.player.*;
import dev.neuralnexus.taterlib.v1_10_2.fabric.resources.FabricResourceKey;

import net.legacyfabric.fabric.api.command.v2.CommandRegistrar;
import net.legacyfabric.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.legacyfabric.fabric.api.networking.v1.ServerPlayConnectionEvents;

@SuppressWarnings("unused")
public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPI.registerBuilder(ResourceKey.Builder.class, FabricResourceKey.Builder::new);
        TaterAPI.registerFactory(ResourceKey.Factory.class, FabricResourceKey.Factory::new);

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FABRIC)) {
            // Initialize plugin data
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> TaterLib.stop());

            // Register Fabric API command events
            CommandRegistrar.EVENT.register(
                    (manager, dedicated) ->
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new FabricCommandRegisterEvent(manager)));

            // Register Fabric API player events
            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, s) ->
                            PlayerEvents.LOGIN.invoke(new FabricPlayerLoginEvent(handler)));
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
