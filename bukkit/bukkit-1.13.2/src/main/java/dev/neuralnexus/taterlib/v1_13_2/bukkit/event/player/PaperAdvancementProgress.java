/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.bukkit.event.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;

import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;

import java.util.Collection;

/** Paper implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
public class PaperAdvancementProgress extends BukkitPlayerAdvancementEvent
        implements PlayerAdvancementEvent.AdvancementProgress {
    private final PlayerAdvancementCriterionGrantEvent event;

    public PaperAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public String advancement() {
        return event.getAdvancement().getKey().getKey();
    }

    @Override
    public Collection<String> criterion() {
        return event.getAdvancement().getCriteria();
    }
}
