/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.neoforge;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterlib.utils.modern.neoforge.event.NeoForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.block.VanillaPlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_16_1.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_16_1.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class NeoForgeEventListener_20_2 {
    public static void init() {
        NeoForge.EVENT_BUS.<BlockEvent.BreakEvent>addListener(
                event ->
                        BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                                new VanillaPlayerBlockBreakEvent(
                                        event.getPlayer().getCommandSenderWorld(),
                                        event.getPlayer(),
                                        event.getPos(),
                                        event.getState(),
                                        new NeoForgeCancellableEventWrapper(event))));

        NeoForge.EVENT_BUS.<RegisterCommandsEvent>addListener(
                event -> {
                    CommandEvents.REGISTER_COMMAND.invoke(
                            new VanillaCommandRegisterEvent(
                                    event.getDispatcher(), event.getCommandSelection()));
                    CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                            new VanillaBrigadierCommandRegisterEvent(
                                    event.getDispatcher(), event.getCommandSelection()));
                });

        NeoForge.EVENT_BUS.<LivingDeathEvent>addListener(
                event ->
                        EntityEvents.DEATH.invoke(
                                new VanillaEntityDeathEvent(event.getEntity(), event.getSource())));

        NeoForge.EVENT_BUS.<AdvancementEvent.AdvancementEarnEvent>addListener(
                event ->
                        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                                new VanillaPlayerAdvancementEvent.AdvancementFinished(
                                        event.getEntity(), event.getAdvancement())));
        NeoForge.EVENT_BUS.<AdvancementEvent.AdvancementProgressEvent>addListener(
                event ->
                        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                                        event.getEntity(),
                                        event.getAdvancement(),
                                        event.getCriterionName())));

        NeoForge.EVENT_BUS.<LivingDeathEvent>addListener(
                event -> {
                    if (event.getEntity() instanceof Player) {
                        PlayerEvents.DEATH.invoke(
                                new VanillaPlayerDeathEvent(
                                        (Player) event.getEntity(), event.getSource()));
                    }
                });
        NeoForge.EVENT_BUS.<PlayerEvent.PlayerLoggedInEvent>addListener(
                event ->
                        PlayerEvents.LOGIN.invoke(
                                new VanillaPlayerLoginEvent((ServerPlayer) event.getEntity())));
        NeoForge.EVENT_BUS.<PlayerEvent.PlayerLoggedOutEvent>addListener(
                event ->
                        PlayerEvents.LOGOUT.invoke(
                                new VanillaPlayerLogoutEvent((ServerPlayer) event.getEntity())));
        NeoForge.EVENT_BUS.<ServerChatEvent>addListener(
                EventPriority.HIGHEST,
                event ->
                        PlayerEvents.MESSAGE.invoke(
                                new VanillaPlayerMessageEvent(
                                        event.getPlayer(),
                                        event.getMessage().getString(),
                                        new NeoForgeCancellableEventWrapper(event))));
        NeoForge.EVENT_BUS.<PlayerEvent.PlayerRespawnEvent>addListener(
                event ->
                        PlayerEvents.RESPAWN.invoke(
                                new VanillaPlayerRespawnEvent(
                                        event.getEntity(), event.getEntity().isAlive())));

        NeoForge.EVENT_BUS.<net.neoforged.neoforge.event.server.ServerStartingEvent>addListener(
                event -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
        NeoForge.EVENT_BUS.<net.neoforged.neoforge.event.server.ServerStartedEvent>addListener(
                event -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}));
        NeoForge.EVENT_BUS.<net.neoforged.neoforge.event.server.ServerStoppingEvent>addListener(
                event -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));
        NeoForge.EVENT_BUS.<net.neoforged.neoforge.event.server.ServerStoppedEvent>addListener(
                event -> ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {}));
    }
}
