/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.Objects;

/** Vanilla implementation of {@link PlayerAdvancementEvent}. */
public class VanillaPlayerAdvancementEvent extends VanillaPlayerEvent
        implements PlayerAdvancementEvent {
    private final Advancement advancement;

    public VanillaPlayerAdvancementEvent(Player player, Advancement advancement) {
        super(player);
        this.advancement = advancement;
    }

    @Override
    public String advancement() {
        return Objects.requireNonNull(advancement.getDisplay()).getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(Player player, Advancement advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends VanillaPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final Advancement advancement;

        public AdvancementProgress(Player player, Advancement advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        @Override
        public Collection<String> criterion() {
            return advancement.getCriteria().keySet();
        }
    }
}
