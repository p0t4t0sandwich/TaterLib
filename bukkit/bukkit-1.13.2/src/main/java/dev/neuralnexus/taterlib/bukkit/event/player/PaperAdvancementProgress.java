package dev.neuralnexus.taterlib.bukkit.event.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import java.util.Collection;

/** Paper implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
public class PaperAdvancementProgress extends BukkitPlayerAdvancementEvent
        implements PlayerAdvancementEvent.AdvancementProgress {
    private final PlayerAdvancementCriterionGrantEvent event;

    public PaperAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String advancement() {
        return event.getAdvancement().getKey().getKey();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<String> criterion() {
        return event.getAdvancement().getCriteria();
    }
}
