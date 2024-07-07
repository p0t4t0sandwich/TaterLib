/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;

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
    public String advancement() {
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
        public Collection<String> criterion() {
            return new HashSet<>(); // advancement.getCriteria().keySet();
        }
    }
}
