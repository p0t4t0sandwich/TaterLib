package dev.neuralnexus.taterlib.neoforge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;

import net.neoforged.neoforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/** NeoForge implementation of {@link PlayerAdvancementEvent}. */
public class NeoForgePlayerAdvancementEvent extends NeoForgePlayerEvent
        implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public NeoForgePlayerAdvancementEvent(AdvancementEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        if (this.event.getAdvancement().value().name().isEmpty()) {
            if (this.event.getAdvancement().value().parent().isPresent()) {
                return this.event.getAdvancement().value().parent().get().getNamespace();
            } else {
                return "";
            }
        }
        return this.event.getAdvancement().value().name().get().getString();
    }

    /** NeoForge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends NeoForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent.AdvancementEarnEvent event) {
            super(event);
        }
    }

    /** NeoForge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends NeoForgePlayerAdvancementEvent
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
