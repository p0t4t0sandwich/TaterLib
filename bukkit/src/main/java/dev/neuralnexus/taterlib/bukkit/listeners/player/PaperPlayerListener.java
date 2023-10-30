package dev.neuralnexus.taterlib.bukkit.listeners.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.player.PaperPlayerAdvancementProgressEvent;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PaperPlayerListener implements Listener {
    /**
     * Called when a player progresses an advancement.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        PlayerListener.onPlayerAdvancementProgress(new PaperPlayerAdvancementProgressEvent(event));
    }
}
