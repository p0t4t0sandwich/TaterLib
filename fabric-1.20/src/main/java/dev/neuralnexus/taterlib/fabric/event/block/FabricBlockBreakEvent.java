package dev.neuralnexus.taterlib.fabric.event.block;

import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Called when a block is broken.
 */
public class FabricBlockBreakEvent extends FabricBlockEvent implements BlockBreakEvent, Cancellable {
    private final CallbackInfo ci;

    public FabricBlockBreakEvent(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        super(world, pos, state, player, ci);
        this.ci = ci;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return ci.isCancelled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancelled) {
        ci.cancel();
    }
}
