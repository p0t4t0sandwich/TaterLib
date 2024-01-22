package dev.neuralnexus.taterlib.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import net.minecraftforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/** Forge implementation of {@link PlayerAdvancementEvent}. */
public class ForgePlayerAdvancementEvent_1_20 extends ForgePlayerEvent
        implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public ForgePlayerAdvancementEvent_1_20(AdvancementEvent event) {
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
    public static class AdvancementFinished extends ForgePlayerAdvancementEvent_1_20
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent event) {
            super(event);
        }
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends ForgePlayerAdvancementEvent_1_20
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEvent event;

        public AdvancementProgress(AdvancementEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> getCriterion() {
            return this.event.getAdvancement().getCriteria().keySet();
        }
    }
}
