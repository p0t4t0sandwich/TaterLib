/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerEvent;

import net.minecraft.advancement.Advancement;
import net.minecraft.entity.living.player.PlayerEntity;

import java.util.Collection;

/** Vanilla implementation of {@link PlayerAdvancementEvent}. */
public class VanillaPlayerAdvancementEvent extends VanillaPlayerEvent
        implements PlayerAdvancementEvent {
    private final Advancement advancement;

    public VanillaPlayerAdvancementEvent(PlayerEntity player, Advancement advancement) {
        super(player);
        this.advancement = advancement;
    }

    @Override
    public String advancement() {
        if (this.advancement.getInfo() != null) {
            return this.advancement.getInfo().getTitle().getString();
        }
        if (this.advancement.getParent() != null
                && this.advancement.getParent().getInfo() != null) {
            return this.advancement.getParent().getInfo().getTitle().getString();
        } else {
            return "";
        }
    }

    /** Vanilla implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(PlayerEntity player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /** Vanilla implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Advancement advancement;

        public AdvancementProgress(
                PlayerEntity player, Advancement advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        @Override
        public Collection<String> criterion() {
            return this.advancement.getCriteria().keySet();
        }
    }
}
