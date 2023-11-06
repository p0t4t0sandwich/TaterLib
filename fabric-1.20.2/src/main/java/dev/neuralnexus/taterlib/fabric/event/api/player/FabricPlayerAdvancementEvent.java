package dev.neuralnexus.taterlib.fabric.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collection;

/**
 * Fabric implementation of {@link PlayerAdvancementEvent}.
 */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent implements PlayerAdvancementEvent {
    private final AdvancementEntry advancement;

    public FabricPlayerAdvancementEvent(PlayerEntity player, AdvancementEntry advancement) {
        super(player);
        this.advancement = advancement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdvancement() {
        return advancement.value().display().get().getTitle().getString();
    }

    /**
     * Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}.
     */
    public static class AdvancementFinished extends FabricPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(PlayerEntity player, AdvancementEntry advancement) {
            super(player, advancement);
        }
    }

    /**
     * Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}.
     */
    public static class AdvancementProgress extends FabricPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEntry advancement;

        public AdvancementProgress(PlayerEntity player, AdvancementEntry advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Collection<String> getCriterion() {
            return advancement.value().criteria().keySet();
        }
    }
}
