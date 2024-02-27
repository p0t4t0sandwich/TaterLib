package dev.neuralnexus.taterlib.bukkit.listeners.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;

import dev.neuralnexus.taterlib.bukkit.adapters.BukkitAdapters;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerAdvancementEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/** Listens for player events. */
public class PaperPlayerListener implements Listener {
    /**
     * Called when a player progresses an advancement.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        BukkitAdapters.player(event.getPlayer()),
                        BukkitAdapters.advancement(event.getAdvancement()),
                        event.getEventName()));
    }
}
