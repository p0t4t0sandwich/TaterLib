package dev.neuralnexus.taterlib.neoforge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/**
 * NeoForge implementation of {@link PlayerAdvancementEvent}.
 */
public class NeoForgePlayerAdvancementEvent extends NeoForgePlayerEvent implements PlayerAdvancementEvent {
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
     * NeoForge implementation of {@link AdvancementFinished}.
     */
    public static class NeoForgeAdvancementFinished extends NeoForgePlayerAdvancementEvent implements AdvancementFinished {
        public NeoForgeAdvancementFinished(AdvancementEvent.AdvancementEarnEvent event) {
            super(event);
        }
    }

    /**
     * NeoForge implementation of {@link AdvancementProgress}.
     */
    public static class NeoForgeAdvancementProgress extends NeoForgePlayerAdvancementEvent implements AdvancementProgress {
        private final AdvancementEvent.AdvancementProgressEvent event;

        public NeoForgeAdvancementProgress(AdvancementEvent.AdvancementProgressEvent event) {
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
