package dev.neuralnexus.taterlib.bukkit.listeners.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import dev.neuralnexus.taterlib.bukkit.event.api.player.PaperAdvancementProgress;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Listens for player events.
 */
public class PaperPlayerListener implements Listener {
    /**
     * Called when a player progresses an advancement.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(new PaperAdvancementProgress(event));
    }
}
