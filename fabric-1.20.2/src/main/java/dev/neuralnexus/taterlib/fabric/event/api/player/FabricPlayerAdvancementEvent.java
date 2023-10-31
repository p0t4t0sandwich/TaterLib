package dev.neuralnexus.taterlib.fabric.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerAdvancementEvent;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collection;

/**
 * Fabric implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent implements AbstractPlayerAdvancementEvent {
    private final AdvancementEntry advancement;

    public FabricPlayerAdvancementEvent(PlayerEntity player, AdvancementEntry advancement) {
        super(player);
        this.advancement = advancement;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        return advancement.value().display().get().getTitle().getString();
    }

    /**
     * Fabric implementation of {@link AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class FabricPlayerAdvancementFinishedEvent extends FabricPlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public FabricPlayerAdvancementFinishedEvent(PlayerEntity player, AdvancementEntry advancement) {
            super(player, advancement);
        }
    }

    /**
     * Fabric implementation of {@link AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent}.
     */
    public static class FabricPlayerAdvancementProgressEvent extends FabricPlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final AdvancementEntry advancement;

        public FabricPlayerAdvancementProgressEvent(PlayerEntity player, AdvancementEntry advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Collection<String> getCriterion() {
            return advancement.value().criteria().keySet();
        }
    }
}
