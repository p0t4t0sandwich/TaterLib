/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.fabric.event.block;

import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterlib.v1_12_2.fabric.player.FabricPlayer;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
            BlockState state,
            BlockEntity blockEntity,
            ItemStack stack,
            CallbackInfo ci) {
        super(world, player, pos, state, blockEntity, stack, ci);
        this.player = player;
        this.ci = ci;
    }

    @Override
    public boolean cancelled() {
        return ci.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        ci.cancel();
    }

    @Override
    public Player player() {
        return new FabricPlayer(player);
    }
}
