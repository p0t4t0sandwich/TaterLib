/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerEvent;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

/** Fabric implementation of {@link PlayerAdvancementEvent}. */
public class VanillaPlayerAdvancementEvent_1_20_2 extends VanillaPlayerEvent
        implements PlayerAdvancementEvent {
    private final AdvancementHolder advancement;

    public VanillaPlayerAdvancementEvent_1_20_2(Player player, AdvancementHolder advancement) {
        super(player);
        this.advancement = advancement;
    }

    @Override
    public String advancement() {
        return advancement.value().display().get().getTitle().getString();
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends VanillaPlayerAdvancementEvent_1_20_2
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(Player player, AdvancementHolder advancement) {
            super(player, advancement);
        }
    }

    /** Fabric implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends VanillaPlayerAdvancementEvent_1_20_2
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementHolder advancement;

        public AdvancementProgress(
                Player player, AdvancementHolder advancement, String criterionName) {
            super(player, advancement);
            this.advancement = advancement;
        }

        @Override
        public Collection<String> criterion() {
            return advancement.value().criteria().keySet();
        }
    }
}
