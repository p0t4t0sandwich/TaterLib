package dev.neuralnexus.taterlib.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/** Sponge implementation of {@link PlayerAdvancementEvent}. */
public class SpongePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public SpongePlayerAdvancementEvent(AdvancementEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        DisplayInfo display = event.getAdvancement().getDisplayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            return display.getTitle().toString();
        } else {
            return event.getAdvancement().getName();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /** Sponge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends SpongePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent.Grant event) {
            super(event);
        }
    }

    /** Sponge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends SpongePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEvent.Grant event;

        public AdvancementProgress(AdvancementEvent.Grant event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> getCriterion() {
            return new HashSet<>();
        }
    }
}
