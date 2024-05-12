package dev.neuralnexus.taterlib.v1_8_8.bukkit.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerEvent;

/** Bukkit implementation of {@link PlayerAdvancementEvent}. */
public class BukkitPlayerAdvancementEvent extends BukkitPlayerEvent
        implements PlayerAdvancementEvent {
    public BukkitPlayerAdvancementEvent(PlayerEvent event) {
        super(event);
    }

    /** {@inheritDoc} */
    @Override
    public String advancement() {
        return null;
    }

    /** Bukkit implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends BukkitPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        private final PlayerAchievementAwardedEvent event;

        public AdvancementFinished(PlayerAchievementAwardedEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public String advancement() {
            return event.getAchievement().toString();
        }
    }
}
