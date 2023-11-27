package dev.neuralnexus.taterlib.fabric.event.block;

import dev.neuralnexus.taterlib.common.block.Block;
import dev.neuralnexus.taterlib.common.event.block.BlockEvent;
import dev.neuralnexus.taterlib.fabric.block.FabricBlock;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block event occurs. */
public class FabricBlockEvent implements BlockEvent {
    private final World world;
    private final BlockPos pos;
    private final BlockState state;
    private final PlayerEntity player;
    private final CallbackInfo ci;

    public FabricBlockEvent(
            World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.player = player;
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public Block getBlock() {
        return new FabricBlock(this.pos, this.state.getBlock());
    }
}
