package dev.neuralnexus.taterlib.forge.listeners.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.forge.event.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.vanilla.event.block.VanillaBlockBreakEvent;

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
        // Assumes player is in the same world as the block, will be more accurate with mixins
        BlockEvents.BLOCK_BREAK.invoke(
                new VanillaBlockBreakEvent(
                        event.getPlayer().getCommandSenderWorld(),
                        event.getPlayer(),
                        event.getPos(),
                        event.getState(),
                        new ForgeCancellableEventWrapper(event)));
    }
}
