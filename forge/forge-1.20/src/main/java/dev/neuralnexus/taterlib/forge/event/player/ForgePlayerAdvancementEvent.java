package dev.neuralnexus.taterlib.forge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;

import net.minecraftforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/** Forge implementation of {@link PlayerAdvancementEvent}. */
public class ForgePlayerAdvancementEvent extends ForgePlayerEvent
        implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public ForgePlayerAdvancementEvent(AdvancementEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        if (event.getAdvancement().getDisplay() != null) {
            return event.getAdvancement().getDisplay().getTitle().getString();
        }
        if (event.getAdvancement().getParent() != null
                && event.getAdvancement().getParent().getDisplay() != null) {
            return this.event.getAdvancement().getParent().getDisplay().getTitle().getString();
        } else {
            return "";
        }
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends ForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent.AdvancementEarnEvent event) {
            super(event);
        }
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends ForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEvent.AdvancementProgressEvent event;

        public AdvancementProgress(AdvancementEvent.AdvancementProgressEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> getCriterion() {
            Iterable<String> criteria = this.event.getAdvancementProgress().getRemainingCriteria();
            Collection<String> collection = new java.util.ArrayList<>();
            criteria.iterator().forEachRemaining(collection::add);
            return collection;
        }
    }
}
