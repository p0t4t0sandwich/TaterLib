package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerEvent;

/**
 * Bukkit implementation of {@link PlayerAdvancementEvent}.
 */
public class BukkitPlayerAdvancementEvent extends BukkitPlayerEvent implements PlayerAdvancementEvent {
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
    public Player getPlayer() {
        return null;
    }

    /**
     * Bukkit implementation of {@link PlayerAdvancementEvent}.
     */
    public static class BukkitAdvancementFinished extends BukkitPlayerAdvancementEvent implements AdvancementFinished {
        private final PlayerAchievementAwardedEvent event;

        public BukkitAdvancementFinished(PlayerAchievementAwardedEvent event) {
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
