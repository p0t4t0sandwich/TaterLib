/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14_4.forge.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.forge.utils.modern.event.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.*;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

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
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        event.getPlayer(),
                        event.getAdvancement(),
                        event.getAdvancement().getCriteria().keySet().toArray(new String[0])[0]));

        // Get the player's advancement progress
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        PlayerAdvancements playerAdvancements =
                server.getPlayerList().getPlayerAdvancements((ServerPlayer) event.getEntity());
        AdvancementProgress progress = playerAdvancements.getOrStartProgress(advancement);

        // Fire the advancement finished event if the advancement is done
        DisplayInfo displayInfo = advancement.getDisplay();
        if (displayInfo != null && displayInfo.shouldAnnounceChat() && progress.isDone()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new VanillaPlayerAdvancementEvent.AdvancementFinished(
                            event.getPlayer(), event.getAdvancement()));
        }
    }

    /**
     * Called when a player dies.
     *
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            PlayerEvents.DEATH.invoke(
                    new VanillaPlayerDeathEvent((Player) event.getEntity(), event.getSource()));
        }
    }

    /**
     * Called when a player logs in.
     *
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent((ServerPlayer) event.getEntity()));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerEvents.LOGOUT.invoke(new VanillaPlayerLogoutEvent((ServerPlayer) event.getEntity()));
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
                        event.getPlayer(),
                        event.getMessage(),
                        new ForgeCancellableEventWrapper(event)));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The player respawn event
     */
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(
                new VanillaPlayerRespawnEvent(event.getPlayer(), event.getEntity().isAlive()));
    }
}
