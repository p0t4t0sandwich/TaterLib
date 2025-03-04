/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.listeners.block;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.block.FabricBlockBreakEvent;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(Block.class)
public class PlayerBlockBreakMixin {
    @Inject(method = "harvestBlock", at = @At("HEAD"), cancellable = true)
    private void onBlockBreak(
            World world, EntityPlayer player, int i, int j, int k, int l, CallbackInfo ci) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                new FabricBlockBreakEvent(
                        world,
                        player,
                        new ChunkCoordinates(i, j, k),
                        l,
                        world.getTileEntity(i, j, k),
                        ci));
    }
}
