/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.listeners.block;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.mixin.MixinCancellableCallbackWrapper;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqPlatform;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.block.VanillaPlayerBlockBreakEvent;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.MOJANG)
@ReqPlatform(not = {Platform.NEOFORGE, Platform.SPONGE})
@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(Block.class)
public class PlayerBlockBreakMixin {
    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void onBreak(
            Level level,
            Player player,
            BlockPos blockPos,
            BlockState blockState,
            BlockEntity blockEntity,
            ItemStack itemStack,
            CallbackInfo ci) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                new VanillaPlayerBlockBreakEvent(
                        level,
                        player,
                        blockPos,
                        blockState,
                        new MixinCancellableCallbackWrapper(ci)));
    }
}
