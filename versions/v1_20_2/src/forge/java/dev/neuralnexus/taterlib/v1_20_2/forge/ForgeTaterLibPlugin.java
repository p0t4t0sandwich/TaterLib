/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.forge;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.utils.modern.forge.event.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.block.VanillaPlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_20_1.forge.ForgeAdvancementListener_20_1;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;

@AConstraint(
        platform = Platform.FORGE,
        version = @Versions(min = MinecraftVersion.V20, max = MinecraftVersion.V20_4))
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().platform().isForge()) {
            TaterAPI.setLoaded(true);
            VanillaBootstrap.init();

            MinecraftForge.EVENT_BUS.<BlockEvent.BreakEvent>addListener(
                    event ->
                            BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                                    new VanillaPlayerBlockBreakEvent(
                                            event.getPlayer().getCommandSenderWorld(),
                                            event.getPlayer(),
                                            event.getPos(),
                                            event.getState(),
                                            new ForgeCancellableEventWrapper(event))));

            MinecraftForge.EVENT_BUS.<LivingDamageEvent>addListener(
                    event ->
                            EntityEvents.DAMAGE.invoke(
                                    new VanillaEntityDamageEvent(
                                            event.getEntity(),
                                            event.getSource(),
                                            event.getAmount(),
                                            new ForgeCancellableEventWrapper(event))));
            MinecraftForge.EVENT_BUS.<LivingDeathEvent>addListener(
                    event ->
                            EntityEvents.DEATH.invoke(
                                    new VanillaEntityDeathEvent(
                                            event.getEntity(), event.getSource())));
            MinecraftForge.EVENT_BUS.<MobSpawnEvent.FinalizeSpawn>addListener(
                    event ->
                            EntityEvents.SPAWN.invoke(
                                    new VanillaEntitySpawnEvent(
                                            event.getEntity(),
                                            new ForgeCancellableEventWrapper(event))));

            if (MetaAPI.instance()
                    .version()
                    .isInRange(MinecraftVersions.V20, MinecraftVersions.V20_1)) {
                MinecraftForge.EVENT_BUS.addListener(
                        ForgeAdvancementListener_20_1::onPlayerAdvancementFinished);
                MinecraftForge.EVENT_BUS.addListener(
                        ForgeAdvancementListener_20_1::onPlayerAdvancementProgress);
            } else if (MetaAPI.instance().version().noLessThan(MinecraftVersions.V20_2)) {
                MinecraftForge.EVENT_BUS.addListener(
                        ForgeAdvancementListener_20_2::onPlayerAdvancementFinished);
                MinecraftForge.EVENT_BUS.addListener(
                        ForgeAdvancementListener_20_2::onPlayerAdvancementProgress);
            }

            MinecraftForge.EVENT_BUS.<LivingDeathEvent>addListener(
                    event -> {
                        if (event.getEntity() instanceof Player) {
                            PlayerEvents.DEATH.invoke(
                                    new VanillaPlayerDeathEvent(
                                            (Player) event.getEntity(), event.getSource()));
                        }
                    });
            MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerLoggedInEvent>addListener(
                    event ->
                            PlayerEvents.LOGIN.invoke(
                                    new VanillaPlayerLoginEvent((ServerPlayer) event.getEntity())));
            MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerLoggedOutEvent>addListener(
                    event ->
                            PlayerEvents.LOGOUT.invoke(
                                    new VanillaPlayerLogoutEvent(
                                            (ServerPlayer) event.getEntity())));
            MinecraftForge.EVENT_BUS.<ServerChatEvent>addListener(
                    EventPriority.HIGHEST,
                    event ->
                            PlayerEvents.MESSAGE.invoke(
                                    new VanillaPlayerMessageEvent(
                                            event.getPlayer(),
                                            event.getMessage().getString(),
                                            new ForgeCancellableEventWrapper(event))));
            MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerRespawnEvent>addListener(
                    event ->
                            PlayerEvents.RESPAWN.invoke(
                                    new VanillaPlayerRespawnEvent(
                                            event.getEntity(), event.getEntity().isAlive())));

            MinecraftForge.EVENT_BUS
                    .<net.minecraftforge.event.server.ServerStartingEvent>addListener(
                            event -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
            MinecraftForge.EVENT_BUS
                    .<net.minecraftforge.event.server.ServerStartedEvent>addListener(
                            event -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}));
            MinecraftForge.EVENT_BUS
                    .<net.minecraftforge.event.server.ServerStoppingEvent>addListener(
                            event -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));
            MinecraftForge.EVENT_BUS
                    .<net.minecraftforge.event.server.ServerStoppedEvent>addListener(
                            event -> ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {}));
        }
    }
}
