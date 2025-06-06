/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.block;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.block.Block;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Vanilla implementation of {@link PlayerBlockBreakEvent}. */
public class VanillaPlayerBlockBreakEvent extends VanillaBlockEvent
        implements PlayerBlockBreakEvent {
    private final PlayerEntity player;
    private final Cancellable cancel;

    public VanillaPlayerBlockBreakEvent(
            BlockPos pos, World world, Block block, PlayerEntity player, Cancellable cancel) {
        super(pos, world, block);
        this.player = player;
        this.cancel = cancel;
    }

    @Override
    public boolean cancelled() {
        return cancel.cancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    @Override
    public Player player() {
        // (Player) this.player;
        return new WrappedPlayer(this.player);
    }
}
