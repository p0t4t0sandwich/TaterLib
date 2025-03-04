/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.forge.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_12_2.forge.event.player.*;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/** Listens for player events. */
public class ForgePlayerListener {
    /**
     * Called when a player finishes/progresses in an advancement.
     *
     * @param event The advancement event
     */
    @SubscribeEvent
    public void onPlayerAdvancement(AdvancementEvent event) {
        Advancement advancement = event.getAdvancement();

        // Fire the generic advancement event
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new ForgePlayerAdvancementEvent.AdvancementProgress(event));

        // Get the player's advancement progress
        MinecraftServer server = (event.getEntity()).getServer();
        if (server == null) {
            return;
        }
        PlayerAdvancements playerAdvancements =
                server.getPlayerList().getPlayerAdvancements((EntityPlayerMP) event.getEntity());
        AdvancementProgress progress = playerAdvancements.getProgress(advancement);

        // Fire the advancement finished event if the advancement is done
        DisplayInfo displayInfo = advancement.getDisplay();
        if (displayInfo != null && displayInfo.shouldAnnounceToChat() && progress.isDone()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new ForgePlayerAdvancementEvent.AdvancementFinished(event));
        }
    }

    /**
     * Called when a player dies.
     *
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {
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
        PlayerEvents.LOGIN.invoke(new ForgePlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerEvents.LOGOUT.invoke(new ForgePlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     *
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        PlayerEvents.MESSAGE.invoke(new ForgePlayerMessageEvent(event));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The player respawn event
     */
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(new ForgePlayerRespawnEvent(event));
    }
}
