/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.vanilla.event.block;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/** Vanilla implementation of {@link PlayerBlockBreakEvent}. */
public class VanillaPlayerBlockBreakEvent extends VanillaBlockEvent
        implements PlayerBlockBreakEvent {
    private final net.minecraft.world.entity.player.Player player;
    private final Cancellable cancel;

    public VanillaPlayerBlockBreakEvent(
            Level level,
            net.minecraft.world.entity.player.Player player,
            BlockPos blockPos,
            BlockState blockState,
            Cancellable cancel) {
        super(level, blockPos, blockState);
        this.player = player;
        this.cancel = cancel;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return cancel.cancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new VanillaPlayer(player);
    }
}
