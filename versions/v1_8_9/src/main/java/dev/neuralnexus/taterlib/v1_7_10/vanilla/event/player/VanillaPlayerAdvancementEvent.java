/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

import java.util.Collection;

/** Vanilla implementation of {@link PlayerAdvancementEvent}. */
public class VanillaPlayerAdvancementEvent extends VanillaPlayerEvent
        implements PlayerAdvancementEvent {
    private final Achievement advancement;

    public VanillaPlayerAdvancementEvent(EntityPlayer player, Achievement advancement) {
        super(player);
        this.advancement = advancement;
    }

    @Override
    public String advancement() {
        return advancement.getStatName().getFormattedText();
    }

    /** Vanilla implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(EntityPlayer player, Achievement advancement) {
            super(player, advancement);
        }
    }

    /** Vanilla implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Achievement advancement;

        public AdvancementProgress(
                EntityPlayer player, Achievement advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        @Override
        public Collection<String> criterion() {
            throw new VersionFeatureNotSupportedException();
        }
    }
}
