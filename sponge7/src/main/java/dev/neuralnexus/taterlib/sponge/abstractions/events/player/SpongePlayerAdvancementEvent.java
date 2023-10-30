package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sponge implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementEvent {
    private final AdvancementEvent event;

    public SpongePlayerAdvancementEvent(AdvancementEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        DisplayInfo display = event.getAdvancement().getDisplayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            return display.getTitle().toString();
        } else {
            return event.getAdvancement().getName();
        }
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
        public SpongePlayerAdvancementFinishedEvent(AdvancementEvent.Grant event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link AbstractPlayerAdvancementProgressEvent}.
     */
    public static class SpongePlayerAdvancementProgressEvent extends SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final AdvancementEvent.Grant event;

        public SpongePlayerAdvancementProgressEvent(AdvancementEvent.Grant event) {
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
