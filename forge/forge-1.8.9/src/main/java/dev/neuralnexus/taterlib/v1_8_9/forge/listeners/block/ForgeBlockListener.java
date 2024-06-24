package dev.neuralnexus.taterlib.v1_8_9.forge.listeners.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_8_9.forge.event.block.ForgeBlockBreakEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Listens for entity events. */
public class ForgeBlockListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(new ForgeBlockBreakEvent(event));
    }
}
