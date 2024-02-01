package dev.neuralnexus.taterlib.v1_20.vanilla.event.block;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

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
    public boolean isCancelled() {
        return cancel.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new VanillaPlayer(player);
    }
}
