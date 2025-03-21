/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.listeners.player;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_7_10.forge.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_7_10.forge.event.player.ForgePlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerRespawnEvent;

import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;

/** Listens for player events. */
public class ForgePlayerListener {
    /**
     * Called when a player finishes/progresses in an advancement.
     *
     * @param event The advancement event
     */
    @SubscribeEvent
    public void onPlayerAdvancement(AchievementEvent event) {
        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementFinished(
                        event.entityPlayer, event.achievement));
    }

    /**
     * Called when a player dies.
     *
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.entity instanceof ServerPlayerEntity) {
            PlayerEvents.DEATH.invoke(new ForgePlayerDeathEvent(event));
        }
    }

    /**
     * Called when a player logs in.
     *
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent(event.player));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerEvents.LOGOUT.invoke(new VanillaPlayerLogoutEvent(event.player));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     *
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        PlayerEvents.MESSAGE.invoke(
                new VanillaPlayerMessageEvent(
                        event.player, event.message, new ForgeCancellableEventWrapper(event)));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The player respawn event
     */
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(
                new VanillaPlayerRespawnEvent(
                        event.player, event.player.dimensionId, event.player.isAlive()));
    }
}
