/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.listeners.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_7_10.forge.ForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.block.VanillaPlayerBlockBreakEvent;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;

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
                        new BlockPos(event.x, event.y, event.z),
                        event.world,
                        event.block,
                        event.getPlayer(),
                        new ForgeCancellableEventWrapper(event)));
    }
}
