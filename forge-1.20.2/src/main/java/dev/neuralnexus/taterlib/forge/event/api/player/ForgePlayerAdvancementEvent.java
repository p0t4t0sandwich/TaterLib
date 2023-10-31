package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerAdvancementEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/**
 * Forge implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class ForgePlayerAdvancementEvent extends ForgePlayerEvent implements AbstractPlayerAdvancementEvent {
    private final AdvancementEvent event;

    public ForgePlayerAdvancementEvent(AdvancementEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        if (!this.event.getAdvancement().value().name().isPresent()) {
            if (this.event.getAdvancement().value().parent().isPresent()) {
                return this.event.getAdvancement().value().parent().get().getNamespace();
            } else {
                return "";
            }
        }
        return this.event.getAdvancement().value().name().get().getString();
    }

    /**
     * Forge implementation of {@link AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class ForgePlayerAdvancementFinishedEvent extends ForgePlayerAdvancementEvent implements AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent {
        public ForgePlayerAdvancementFinishedEvent(AdvancementEvent.AdvancementEarnEvent event) {
            super(event);
        }
    }

    /**
     * Forge implementation of {@link AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent}.
     */
    public static class ForgePlayerAdvancementProgressEvent extends ForgePlayerAdvancementEvent implements AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent {
        private final AdvancementEvent.AdvancementProgressEvent event;

        public ForgePlayerAdvancementProgressEvent(AdvancementEvent.AdvancementProgressEvent event) {
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
