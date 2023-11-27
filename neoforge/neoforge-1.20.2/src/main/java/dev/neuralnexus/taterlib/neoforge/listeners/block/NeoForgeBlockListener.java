package dev.neuralnexus.taterlib.neoforge.listeners.block;

import dev.neuralnexus.taterlib.common.event.api.BlockEvents;
import dev.neuralnexus.taterlib.neoforge.event.block.NeoForgeBlockBreakEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

/** Listens for entity events. */
public class NeoForgeBlockListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockEvents.BLOCK_BREAK.invoke(new NeoForgeBlockBreakEvent(event));
    }
}
