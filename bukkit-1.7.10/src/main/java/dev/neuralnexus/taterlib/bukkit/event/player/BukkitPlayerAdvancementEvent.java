package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerEvent;

/**
 * Bukkit implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class BukkitPlayerAdvancementEvent extends BukkitPlayerEvent implements AbstractPlayerAdvancementEvent {
    BukkitPlayerAdvancementEvent(PlayerEvent event) {
        super(event);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return null;
    }

    /**
     * Bukkit implementation of {@link AbstractPlayerAdvancementEvent}.
     */
    public static class BukkitPlayerAdvancementFinishedEvent extends BukkitPlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        private final PlayerAchievementAwardedEvent event;

        public BukkitPlayerAdvancementFinishedEvent(PlayerAchievementAwardedEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String getAdvancement() {
            return event.getAchievement().toString();
        }
    }
}
