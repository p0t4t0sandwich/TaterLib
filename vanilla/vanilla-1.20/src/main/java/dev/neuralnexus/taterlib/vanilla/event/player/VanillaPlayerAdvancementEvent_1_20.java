package dev.neuralnexus.taterlib.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class VanillaPlayerAdvancementEvent_1_20 extends VanillaPlayerEvent
        implements PlayerAdvancementEvent {
    private final Advancement advancement;

    public VanillaPlayerAdvancementEvent_1_20(Player player, Advancement advancement) {
        super(player);
        this.advancement = advancement;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        return advancement.getDisplay().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends VanillaPlayerAdvancementEvent_1_20
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(Player player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends VanillaPlayerAdvancementEvent_1_20
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
