package dev.neuralnexus.taterlib.v1_13_2.bukkit.listeners.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.event.player.PaperAdvancementProgress;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/** Listener for player events. */
public class PaperPlayerListener implements Listener {
    /**
     * Called when a player progresses an advancement.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(new PaperAdvancementProgress(event));
    }
}
