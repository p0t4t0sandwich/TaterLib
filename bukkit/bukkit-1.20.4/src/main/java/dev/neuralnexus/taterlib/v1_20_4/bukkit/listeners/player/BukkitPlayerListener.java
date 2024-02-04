package dev.neuralnexus.taterlib.v1_20_4.bukkit.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.event.player.VanillaPlayerAdvancementEvent_1_20_2;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.event.BukkitCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.event.player.BukkitPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.event.player.BukkitPlayerLogoutEvent;

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
        org.bukkit.advancement.Advancement advancement = event.getAdvancement();
        if (advancement.getDisplay() != null && advancement.getDisplay().shouldAnnounceChat()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new VanillaPlayerAdvancementEvent_1_20_2.AdvancementFinished(
                            BukkitAdapter.get().getPlayer(event.getPlayer()),
                            BukkitAdapter.get().getAdvancement(event.getAdvancement())));
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
                        BukkitAdapter.get().getPlayer(event.getEntity()),
                        BukkitAdapter.get().getLastDamageSource(event.getEntity())));
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
                        BukkitAdapter.get().getPlayer(event.getPlayer()),
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
                        BukkitAdapter.get().getPlayer(event.getPlayer()),
                        event.getPlayer().getHealth() > 0.0F));
    }
}
