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
     * Sponge implementation of {@link AdvancementFinished}.
     */
    public static class SpongeAdvancementFinished extends SpongePlayerAdvancementEvent implements AdvancementFinished {
        public SpongeAdvancementFinished(GrantAchievementEvent.TargetPlayer event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link AdvancementProgress}.
     */
    public static class SpongeAdvancementProgress extends SpongePlayerAdvancementEvent implements AdvancementProgress {
        private final GrantAchievementEvent.TargetPlayer event;

        public SpongeAdvancementProgress(GrantAchievementEvent.TargetPlayer event) {
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
