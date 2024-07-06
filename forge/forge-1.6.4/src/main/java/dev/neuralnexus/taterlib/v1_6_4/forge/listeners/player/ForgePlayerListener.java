/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.listeners.player;

import cpw.mods.fml.common.IPlayerTracker;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_6_4.forge.event.player.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/** Listens for player events. */
public class ForgePlayerListener implements IPlayerTracker {
    /**
     * Called when a player finishes/progresses in an advancement.
     *
     * @param event The advancement event
     */
    //    @ForgeSubscribe
    //    public void onPlayerAdvancement(AchievementEvent event) {
    //        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
    //                new ForgePlayerAdvancementEvent.AdvancementFinished(event));
    //    }

    /**
     * Called when a player dies.
     *
     * @param event The player death event
     */
    @ForgeSubscribe
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayerMP) {
            PlayerEvents.DEATH.invoke(new ForgePlayerDeathEvent(event));
        }
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     *
     * @param event The player message event
     */
    @ForgeSubscribe(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        PlayerEvents.MESSAGE.invoke(new ForgePlayerMessageEvent(event));
    }

    /**
     * Called when a player logs in.
     *
     * @param player The player
     */
    @Override
    public void onPlayerLogin(EntityPlayer player) {
        PlayerEvents.LOGIN.invoke(new ForgePlayerLoginEvent(player));
    }

    /**
     * Called when a player logs out.
     *
     * @param player The player
     */
    @Override
    public void onPlayerLogout(EntityPlayer player) {
        PlayerEvents.LOGOUT.invoke(new ForgePlayerLogoutEvent(player));
    }

    /**
     * Called when a player respawns.
     *
     * @param player The player
     */
    @Override
    public void onPlayerRespawn(EntityPlayer player) {
        PlayerEvents.RESPAWN.invoke(new ForgePlayerRespawnEvent(player));
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {}
}
