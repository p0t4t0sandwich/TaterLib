package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import org.spongepowered.api.event.achievement.GrantAchievementEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sponge implementation of {@link PlayerAdvancementEvent}.
 */
public class SpongePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final GrantAchievementEvent.TargetPlayer event;

    public SpongePlayerAdvancementEvent(GrantAchievementEvent.TargetPlayer event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        return event.getAchievement().getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /**
     * Sponge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}.
     */
    public static class AdvancementFinished extends SpongePlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(GrantAchievementEvent.TargetPlayer event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}.
     */
    public static class AdvancementProgress extends SpongePlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementProgress {
        private final GrantAchievementEvent.TargetPlayer event;

        public AdvancementProgress(GrantAchievementEvent.TargetPlayer event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Collection<String> getCriterion() {
            return new HashSet<>();
        }
    }
}
