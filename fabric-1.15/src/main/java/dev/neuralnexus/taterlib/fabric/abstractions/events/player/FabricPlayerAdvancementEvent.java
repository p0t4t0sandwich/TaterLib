package dev.neuralnexus.taterlib.fabric.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerAdvancementEvent;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collection;

/**
 * Fabric implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent implements AbstractPlayerAdvancementEvent {
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
     * Fabric implementation of {@link AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class FabricPlayerAdvancementFinishedEvent extends FabricPlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public FabricPlayerAdvancementFinishedEvent(PlayerEntity player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /**
     * Fabric implementation of {@link AbstractPlayerAdvancementProgressEvent}.
     */
    public static class FabricPlayerAdvancementProgressEvent extends FabricPlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final Advancement advancement;

        public FabricPlayerAdvancementProgressEvent(PlayerEntity player, Advancement advancement, String criterionName) {
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
