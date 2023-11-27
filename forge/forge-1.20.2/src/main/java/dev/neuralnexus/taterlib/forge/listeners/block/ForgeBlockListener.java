package dev.neuralnexus.taterlib.forge.listeners.block;

import dev.neuralnexus.taterlib.common.event.api.BlockEvents;
import dev.neuralnexus.taterlib.forge.event.block.ForgeBlockBreakEvent;

import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Listens for entity events. */
public class ForgeBlockListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockEvents.BLOCK_BREAK.invoke(new ForgeBlockBreakEvent(event));
    }
}
