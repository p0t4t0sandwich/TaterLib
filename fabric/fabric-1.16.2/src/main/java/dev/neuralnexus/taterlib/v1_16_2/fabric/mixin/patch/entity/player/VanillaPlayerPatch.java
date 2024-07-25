/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_2.fabric.mixin.patch.entity.player;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16.vanilla.entity.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_16.vanilla.world.VanillaWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/** Patch mixin for VanillaPlayer */
@ReqPlatform(Platform.FABRIC)
@ReqMCVersion(min = MinecraftVersion.V1_16_2, max = MinecraftVersion.V1_16_5)
@Mixin(value = VanillaPlayer.class, remap = false)
public class VanillaPlayerPatch {
    @Final @Shadow private Player player;

    /**
     * @author p0t4t0sandwich
     * @reason Patch VanillaPlayer.setSpawn
     */
    @Overwrite
    public void setSpawn(Location location, boolean forced) {
        ((net.minecraft.server.level.ServerPlayer) player)
                .setRespawnPosition(
                        ((VanillaWorld) location.world()).world().dimension(),
                        new BlockPos(location.x(), location.y(), location.z()),
                        0.0F,
                        forced,
                        false);
    }
}
