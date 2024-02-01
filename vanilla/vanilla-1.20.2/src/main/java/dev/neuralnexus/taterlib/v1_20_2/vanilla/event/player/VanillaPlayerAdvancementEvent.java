package dev.neuralnexus.taterlib.v1_20_2.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerEvent;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class VanillaPlayerAdvancementEvent extends VanillaPlayerEvent
        implements PlayerAdvancementEvent {
    private final AdvancementHolder advancement;

    public VanillaPlayerAdvancementEvent(Player player, AdvancementHolder advancement) {
        super(player);
        this.advancement = advancement;
    }

    /** {@inheritDoc} */
    @Override
    public String getAdvancement() {
        return advancement.value().display().get().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(Player player, AdvancementHolder advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementHolder advancement;

        public AdvancementProgress(
                Player player, AdvancementHolder advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> getCriterion() {
            return advancement.value().criteria().keySet();
        }
    }
}
