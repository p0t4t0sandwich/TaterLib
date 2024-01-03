package dev.neuralnexus.taterlib.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent
        implements PlayerAdvancementEvent {
    private final Advancement advancement;

    public FabricPlayerAdvancementEvent(Player player, Advancement advancement) {
        super(player);
        this.advancement = advancement;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        return advancement.getDisplay().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(Player player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Advancement advancement;

        public AdvancementProgress(Player player, Advancement advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> getCriterion() {
            return advancement.getCriteria().keySet();
        }
    }
}
