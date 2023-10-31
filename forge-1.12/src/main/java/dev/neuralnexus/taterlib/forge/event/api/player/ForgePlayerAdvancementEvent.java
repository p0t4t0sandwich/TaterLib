package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraftforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/**
 * Forge implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class ForgePlayerAdvancementEvent implements AbstractPlayerAdvancementEvent {
    private final AdvancementEvent event;

    public ForgePlayerAdvancementEvent(AdvancementEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        if (event.getAdvancement().getDisplay() != null) {
            return event.getAdvancement().getDisplay().getTitle().getFormattedText();
        }
        if (event.getAdvancement().getParent() != null && event.getAdvancement().getParent().getDisplay() != null) {
            return this.event.getAdvancement().getParent().getDisplay().getTitle().getFormattedText();
        } else {
            return "";
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new ForgePlayer(event.getEntityPlayer());
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
