package dev.neuralnexus.taterlib.v1_7_10.fabric.event.block;

import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_7_10.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block is broken. */
public class FabricBlockBreakEvent extends FabricBlockEvent implements PlayerBlockBreakEvent {
    private final CallbackInfo ci;
    private final PlayerEntity player;

    public FabricBlockBreakEvent(
            World world,
            PlayerEntity player,
            BlockPos pos,
            int state,
            BlockEntity blockEntity,
            CallbackInfo ci) {
        super(world, player, pos, state, blockEntity, ci);
        this.player = player;
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return ci.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        ci.cancel();
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new FabricPlayer(player);
    }
}