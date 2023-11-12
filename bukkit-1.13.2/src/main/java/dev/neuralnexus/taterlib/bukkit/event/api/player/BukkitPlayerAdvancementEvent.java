package dev.neuralnexus.taterlib.bukkit.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerEvent;

/**
 * Bukkit implementation of {@link PlayerAdvancementEvent}.
 */
public class BukkitPlayerAdvancementEvent extends BukkitPlayerEvent implements PlayerAdvancementEvent {
    BukkitPlayerAdvancementEvent(PlayerEvent event) {
        super(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdvancement() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return null;
    }

    /**
     * Bukkit implementation of {@link PlayerAdvancementEvent.AdvancementFinished}.
     */
    public static class AdvancementFinished extends BukkitPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementFinished {
        private final PlayerAdvancementDoneEvent event;

        public AdvancementFinished(PlayerAdvancementDoneEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getAdvancement() {
            return event.getAdvancement().getKey().getNamespace();
        }
    }
}
