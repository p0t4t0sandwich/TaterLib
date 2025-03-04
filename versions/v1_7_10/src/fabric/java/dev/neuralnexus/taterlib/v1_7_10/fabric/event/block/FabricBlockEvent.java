/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.block.WrappedBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block event occurs. */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class FabricBlockEvent implements BlockEvent {
    private final World world;
    private final EntityPlayer player;
    private final ChunkCoordinates pos;
    private final int state;
    private final TileEntity blockEntity;
    private final CallbackInfo ci;

    public FabricBlockEvent(
            World world,
            EntityPlayer player,
            ChunkCoordinates pos,
            int state,
            TileEntity blockEntity,
            CallbackInfo ci) {
        this.world = world;
        this.player = player;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
        this.ci = ci;
    }

    @Override
    public Block block() {
        return new WrappedBlock(this.pos, this.blockEntity.getBlockType());
    }
}
