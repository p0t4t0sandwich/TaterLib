package dev.neuralnexus.taterlib.fabric.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.fabric.block.FabricBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block event occurs. */
public class FabricBlockEvent implements BlockEvent {
    private final World world;
    private final PlayerEntity player;
    private final BlockPos pos;
    private final BlockState state;
    private final BlockEntity blockEntity;
    private final CallbackInfo ci;

    public FabricBlockEvent(
            World world,
            PlayerEntity player,
            BlockPos pos,
            BlockState state,
            BlockEntity blockEntity,
            CallbackInfo ci) {
        this.world = world;
        this.player = player;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public Block block() {
        return new FabricBlock(this.pos, this.state.getBlock());
    }
}
