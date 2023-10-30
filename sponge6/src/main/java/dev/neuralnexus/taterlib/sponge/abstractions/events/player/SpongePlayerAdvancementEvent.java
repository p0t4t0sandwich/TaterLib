package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.event.achievement.GrantAchievementEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sponge implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementEvent {
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
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /**
     * Sponge implementation of {@link AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class SpongePlayerAdvancementFinishedEvent extends SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public SpongePlayerAdvancementFinishedEvent(GrantAchievementEvent.TargetPlayer event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link AbstractPlayerAdvancementProgressEvent}.
     */
    public static class SpongePlayerAdvancementProgressEvent extends SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final GrantAchievementEvent.TargetPlayer event;

        public SpongePlayerAdvancementProgressEvent(GrantAchievementEvent.TargetPlayer event) {
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
