/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;

import net.minecraft.entity.player.EntityPlayer;

import java.util.Collection;
import java.util.HashSet;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class FabricPlayerAdvancementEvent extends FabricPlayerEvent
        implements PlayerAdvancementEvent {
    private final Object advancement;

    public FabricPlayerAdvancementEvent(EntityPlayer player, Object advancement) {
        super(player);
        this.advancement = advancement;
    }

    @Override
    public String advancement() {
        return advancement.toString(); // .getDisplay().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(EntityPlayer player, Object advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    public static class AdvancementProgress extends FabricPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Object advancement;

        public AdvancementProgress(EntityPlayer player, Object advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        @Override
        public Collection<String> criterion() {
            return new HashSet<>(); // advancement.getCriteria().keySet();
        }
    }
}
