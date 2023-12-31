package dev.neuralnexus.taterlib.bukkit.event.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;

import java.util.Collection;

/**
 * Paper implementation of {@link PlayerAdvancementEvent.AdvancementProgress}.
 */
public class PaperAdvancementProgress extends BukkitPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementProgress {
    private final PlayerAdvancementCriterionGrantEvent event;

    public PaperAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdvancement() {
        return event.getAdvancement().getKey().getNamespace();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getCriterion() {
        return event.getAdvancement().getCriteria();
    }
}
