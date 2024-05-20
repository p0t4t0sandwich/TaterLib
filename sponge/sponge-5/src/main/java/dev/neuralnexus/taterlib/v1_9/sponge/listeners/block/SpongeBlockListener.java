package dev.neuralnexus.taterlib.v1_9.sponge.listeners.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_9.sponge.event.block.SpongeBlockBreakEvent;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

/** Listens for entity events. */
public class SpongeBlockListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @Listener
    public void onBlockBreak(ChangeBlockEvent.Pre event) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(new SpongeBlockBreakEvent(event));
    }
}
