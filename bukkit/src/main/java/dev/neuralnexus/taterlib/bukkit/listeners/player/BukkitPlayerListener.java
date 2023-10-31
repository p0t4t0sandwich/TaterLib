package dev.neuralnexus.taterlib.bukkit.listeners.player;

import dev.neuralnexus.taterlib.bukkit.event.api.player.*;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

/**
 * Listens for player events.
 */
public class BukkitPlayerListener implements Listener {
    /**
     * Called when a player completes an advancement.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        Advancement advancement = event.getAdvancement();
        if (advancement.getDisplay() != null && advancement.getDisplay().shouldAnnounceChat()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(new BukkitPlayerAdvancementEvent.AdvancementFinished(event));
        }
    }

    /**
     * Called when a player dies.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        PlayerEvents.DEATH.invoke(new BukkitPlayerDeathEvent(event));
    }

    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        PlayerEvents.LOGIN.invoke(new BukkitPlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        PlayerEvents.LOGOUT.invoke(new BukkitPlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        PlayerEvents.MESSAGE.invoke(new BukkitPlayerMessageEvent(event));
    }

    /**
     * Called when a player respawns.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(new BukkitPlayerRespawnEvent(event));
    }
}
