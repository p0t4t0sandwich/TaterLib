package dev.neuralnexus.taterlib.fabric.event.block;

import dev.neuralnexus.taterlib.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block is broken. */
public class FabricBlockBreakEvent extends FabricBlockEvent implements BlockBreakEvent {
    private final CallbackInfo ci;
    private final PlayerEntity player;

    public FabricBlockBreakEvent(
            World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        super(world, pos, state, player, ci);
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
        return new FabricPlayer(player);
    }
}
