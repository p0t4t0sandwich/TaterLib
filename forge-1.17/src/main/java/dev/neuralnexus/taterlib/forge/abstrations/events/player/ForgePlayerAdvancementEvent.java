package dev.neuralnexus.taterlib.forge.abstrations.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerAdvancementEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

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
    public static class ForgePlayerAdvancementFinishedEvent extends ForgePlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public ForgePlayerAdvancementFinishedEvent(AdvancementEvent event) {
            super(event);
        }
    }

    /**
     * Forge implementation of {@link AbstractPlayerAdvancementProgressEvent}.
     */
    public static class ForgePlayerAdvancementProgressEvent extends ForgePlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final AdvancementEvent event;

        public ForgePlayerAdvancementProgressEvent(AdvancementEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Collection<String> getCriterion() {
            return this.event.getAdvancement().getCriteria().keySet();
        }
    }
}
