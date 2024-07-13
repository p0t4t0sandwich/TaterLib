/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19_3.fabric.mixin.patch.player;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.entity.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_19.vanilla.world.VanillaWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch mixin for VanillaPlayer 1.19.3. */
@ReqMCVersion(MinecraftVersion.V1_19_3)
@Mixin(value = VanillaPlayer.class, remap = false)
public class VanillaPlayerMixin_1_19_3 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.3
     */
    @Overwrite
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayer) ((VanillaPlayer) (Object) (this)).player())
                .setRespawnPosition(
                        ((VanillaWorld) location.world()).world().dimension(),
                        new BlockPos(location.x(), location.y(), location.z()),
                        0.0F,
                        forced,
                        false);
    }
}
