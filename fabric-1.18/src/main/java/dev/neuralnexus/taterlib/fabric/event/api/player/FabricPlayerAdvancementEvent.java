package dev.neuralnexus.taterlib.fabric.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collection;

/**
 * Fabric implementation of {@link PlayerAdvancementEvent}.
 */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent implements PlayerAdvancementEvent {
    private final Advancement advancement;

    public FabricPlayerAdvancementEvent(PlayerEntity player, Advancement advancement) {
        super(player);
        this.advancement = advancement;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        return advancement.getDisplay().getTitle().getString();
    }

    /**
     * Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}.
     */
    public static class AdvancementFinished extends FabricPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(PlayerEntity player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /**
     * Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}.
     */
    public static class AdvancementProgress extends FabricPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementProgress {
        private final Advancement advancement;

        public AdvancementProgress(PlayerEntity player, Advancement advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Collection<String> getCriterion() {
            return advancement.getCriteria().keySet();
        }
    }
}
