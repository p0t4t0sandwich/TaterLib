package dev.neuralnexus.taterlib.vanilla.event.block;

import dev.neuralnexus.taterlib.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Vanilla implementation of {@link BlockBreakEvent}. */
public class VanillaBlockBreakEvent extends VanillaBlockEvent implements BlockBreakEvent {
    private final CallbackInfo ci;
    private final net.minecraft.world.entity.player.Player player;

    public VanillaBlockBreakEvent(
            Level level,
            net.minecraft.world.entity.player.Player player,
            BlockPos blockPos,
            BlockState blockState,
            CallbackInfo ci) {
        super(level, blockPos, blockState);
        this.player = player;
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return ci.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        ci.cancel();
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new VanillaPlayer(player);
    }
}
