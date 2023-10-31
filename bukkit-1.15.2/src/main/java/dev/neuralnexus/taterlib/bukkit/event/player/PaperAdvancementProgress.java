package dev.neuralnexus.taterlib.bukkit.event.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;

import java.util.Collection;

/**
 * Paper implementation of {@link PlayerAdvancementEvent}.
 */
public class PaperAdvancementProgress extends BukkitPlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementProgress, Cancellable {
    private final PlayerAdvancementCriterionGrantEvent event;

    public PaperAdvancementProgress(PlayerAdvancementCriterionGrantEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        return event.getAdvancement().getKey().getNamespace();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Collection<String> getCriterion() {
        return event.getAdvancement().getCriteria();
    }
}
