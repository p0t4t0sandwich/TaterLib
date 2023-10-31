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
     * Fabric implementation of {@link AdvancementFinished}.
     */
    public static class FabricAdvancementFinished extends FabricPlayerAdvancementEvent implements AdvancementFinished {
        public FabricAdvancementFinished(PlayerEntity player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /**
     * Fabric implementation of {@link AdvancementProgress}.
     */
    public static class FabricAdvancementProgress extends FabricPlayerAdvancementEvent implements AdvancementProgress {
        private final Advancement advancement;

        public FabricAdvancementProgress(PlayerEntity player, Advancement advancement, String criterionName) {
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
