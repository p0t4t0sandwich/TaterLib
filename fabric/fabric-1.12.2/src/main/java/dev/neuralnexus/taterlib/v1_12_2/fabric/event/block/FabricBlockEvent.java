/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_12_2.fabric.block.FabricBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block event occurs. */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class FabricBlockEvent implements BlockEvent {
    private final World world;
    private final PlayerEntity player;
    private final BlockPos pos;
    private final BlockState state;
    private final BlockEntity blockEntity;
    private final ItemStack stack;
    private final CallbackInfo ci;

    public FabricBlockEvent(
            World world,
            PlayerEntity player,
            BlockPos pos,
            BlockState state,
            BlockEntity blockEntity,
            ItemStack stack,
            CallbackInfo ci) {
        this.world = world;
        this.player = player;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
        this.stack = stack;
        this.ci = ci;
    }

    @Override
    public Block block() {
        return new FabricBlock(this.pos, this.state.getBlock());
    }
}
