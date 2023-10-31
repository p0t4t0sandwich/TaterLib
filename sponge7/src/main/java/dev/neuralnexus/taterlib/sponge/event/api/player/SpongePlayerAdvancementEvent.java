package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sponge implementation of {@link PlayerAdvancementEvent}.
 */
public class SpongePlayerAdvancementEvent implements PlayerAdvancementEvent {
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
    public Player getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /**
     * Sponge implementation of {@link AdvancementFinished}.
     */
    public static class SpongeAdvancementFinished extends SpongePlayerAdvancementEvent implements AdvancementFinished {
        public SpongeAdvancementFinished(AdvancementEvent.Grant event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link AdvancementProgress}.
     */
    public static class SpongeAdvancementProgress extends SpongePlayerAdvancementEvent implements AdvancementProgress {
        private final AdvancementEvent.Grant event;

        public SpongeAdvancementProgress(AdvancementEvent.Grant event) {
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
