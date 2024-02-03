package dev.neuralnexus.taterlib.v1_20_2.bukkit.listeners.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_20_2.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.event.player.VanillaPlayerAdvancementEvent_1_20_2;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/** Listens for player events. */
public class PaperPlayerAdvancementListener implements Listener {
    /**
     * Called when a player progresses an advancement.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent_1_20_2.AdvancementProgress(
                        BukkitAdapter.get().getPlayer(event.getPlayer()),
                        BukkitAdapter.get().getAdvancement(event.getAdvancement()),
                        event.getEventName()));
    }
}
