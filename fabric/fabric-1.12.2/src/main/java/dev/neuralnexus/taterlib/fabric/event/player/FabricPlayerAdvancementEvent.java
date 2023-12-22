package dev.neuralnexus.taterlib.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Collection;
import java.util.HashSet;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent
        implements PlayerAdvancementEvent {
    private final Object advancement;

    public FabricPlayerAdvancementEvent(PlayerEntity player, Object advancement) {
        super(player);
        this.advancement = advancement;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        return advancement.toString(); // .getDisplay().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(PlayerEntity player, Object advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Object advancement;

        public AdvancementProgress(PlayerEntity player, Object advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> getCriterion() {
            return new HashSet<>(); // advancement.getCriteria().keySet();
        }
    }
}
