package dev.neuralnexus.taterlib.bukkit.listeners.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.player.PaperPlayerAdvancementProgressEvent;
import dev.neuralnexus.taterlib.common.event.player.PlayerEvents;
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
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(new PaperPlayerAdvancementProgressEvent(event));
    }
}
