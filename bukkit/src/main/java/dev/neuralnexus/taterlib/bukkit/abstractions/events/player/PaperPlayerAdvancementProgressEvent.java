package dev.neuralnexus.taterlib.bukkit.abstractions.events.player;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.AbstractCancellableEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerAdvancementEvent;

import java.util.Collection;

/**
 * Paper implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class PaperPlayerAdvancementProgressEvent extends BukkitPlayerAdvancementEvent implements AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent, AbstractCancellableEvent {
    private final PlayerAdvancementCriterionGrantEvent event;

    public PaperPlayerAdvancementProgressEvent(PlayerAdvancementCriterionGrantEvent event) {
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
