/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.forge.listeners.block;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.block.VanillaPlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_8_9.forge.ForgeCancellableEventWrapper;

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
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                new VanillaPlayerBlockBreakEvent(
                        event.pos,
                        event.world,
                        event.state.getBlock(),
                        event.getPlayer(),
                        new ForgeCancellableEventWrapper(event)));
    }
}
