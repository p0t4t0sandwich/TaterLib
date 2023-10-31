package dev.neuralnexus.taterlib.neoforge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerAdvancementEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/**
 * NeoForge implementation of {@link AbstractPlayerAdvancementEvent}.
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
        if (this.event.getAdvancement().value().name().isEmpty()) {
            if (this.event.getAdvancement().value().parent().isPresent()) {
                return this.event.getAdvancement().value().parent().get().getNamespace();
            } else {
                return "";
            }
        }
        return this.event.getAdvancement().value().name().get().getString();
    }

    /**
     * NeoForge implementation of {@link AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class NeoForgePlayerAdvancementFinishedEvent extends NeoForgePlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public NeoForgePlayerAdvancementFinishedEvent(AdvancementEvent.AdvancementEarnEvent event) {
            super(event);
        }
    }

    /**
     * NeoForge implementation of {@link AbstractPlayerAdvancementProgressEvent}.
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
