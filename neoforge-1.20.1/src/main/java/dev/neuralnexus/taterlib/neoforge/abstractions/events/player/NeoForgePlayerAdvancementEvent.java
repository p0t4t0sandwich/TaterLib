package dev.neuralnexus.taterlib.neoforge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerAdvancementEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/**
 * Forge implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class NeoForgePlayerAdvancementEvent extends NeoForgePlayerEvent implements AbstractPlayerAdvancementEvent {
    private final AdvancementEvent event;

    public NeoForgePlayerAdvancementEvent(AdvancementEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        if (event.getAdvancement().getDisplay() != null) {
            return event.getAdvancement().getDisplay().getTitle().getString();
        }
        if (event.getAdvancement().getParent() != null && event.getAdvancement().getParent().getDisplay() != null) {
            return this.event.getAdvancement().getParent().getDisplay().getTitle().getString();
        } else {
            return "";
        }
    }

    /**
     * Forge implementation of {@link AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class NeoForgePlayerAdvancementFinishedEvent extends NeoForgePlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public NeoForgePlayerAdvancementFinishedEvent(AdvancementEvent.AdvancementEarnEvent event) {
            super(event);
        }
    }

    /**
     * Forge implementation of {@link AbstractPlayerAdvancementProgressEvent}.
     */
    public static class NeoForgePlayerAdvancementProgressEvent extends NeoForgePlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final AdvancementEvent.AdvancementProgressEvent event;

        public NeoForgePlayerAdvancementProgressEvent(AdvancementEvent.AdvancementProgressEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Collection<String> getCriterion() {
            Iterable<String> criteria = this.event.getAdvancementProgress().getRemainingCriteria();
            Collection<String> collection = new java.util.ArrayList<>();
            criteria.iterator().forEachRemaining(collection::add);
            return collection;
        }
    }
}
