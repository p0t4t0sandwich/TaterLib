/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;

import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collection;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent
        implements PlayerAdvancementEvent {
    private final Advancement advancement;

    public FabricPlayerAdvancementEvent(PlayerEntity player, Advancement advancement) {
        super(player);
        this.advancement = advancement;
    }

    @Override
    public String advancement() {
        return advancement.getDisplay().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(PlayerEntity player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Advancement advancement;

        public AdvancementProgress(
                PlayerEntity player, Advancement advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

            @Override
        public Collection<String> criterion() {
            return advancement.getCriteria().keySet();
        }
    }
}
