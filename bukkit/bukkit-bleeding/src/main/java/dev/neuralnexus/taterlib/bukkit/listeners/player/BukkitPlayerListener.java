package dev.neuralnexus.taterlib.bukkit.listeners.player;

import dev.neuralnexus.taterlib.bukkit.adapters.BukkitAdapters;
import dev.neuralnexus.taterlib.bukkit.event.BukkitCancellableEventWrapper;
import dev.neuralnexus.taterlib.bukkit.event.player.*;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerDeathEvent;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerMessageEvent;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerRespawnEvent;

import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

/** Listens for player events. */
public class BukkitPlayerListener implements Listener {
    /**
     * Called when a player completes an advancement.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        Advancement advancement = event.getAdvancement();
        if (advancement.getDisplay() != null && advancement.getDisplay().shouldAnnounceChat()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new VanillaPlayerAdvancementEvent.AdvancementFinished(
                            BukkitAdapters.player(event.getPlayer()),
                            BukkitAdapters.advancement(event.getAdvancement())));
        }
    }

    /**
     * Called when a player dies.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        PlayerEvents.DEATH.invoke(
                new VanillaPlayerDeathEvent(
                        BukkitAdapters.player(event.getEntity()),
                        BukkitAdapters.lastDamageSource(event.getEntity())));
    }

    /**
     * Called when a player logs in.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        PlayerEvents.LOGIN.invoke(new BukkitPlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        PlayerEvents.LOGOUT.invoke(new BukkitPlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     *
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        PlayerEvents.MESSAGE.invoke(
                new VanillaPlayerMessageEvent(
                        BukkitAdapters.player(event.getPlayer()),
                        event.getMessage(),
                        new BukkitCancellableEventWrapper<>(event)));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(
                new VanillaPlayerRespawnEvent(
                        BukkitAdapters.player(event.getPlayer()),
                        event.getPlayer().getHealth() > 0.0F));
    }
}
