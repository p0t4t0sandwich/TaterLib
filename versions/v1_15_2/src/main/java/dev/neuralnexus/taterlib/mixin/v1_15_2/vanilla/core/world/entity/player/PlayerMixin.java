/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_15_2.vanilla.core.world.entity.player;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V15, max = MinecraftVersion.V15_2)
@Mixin(Player.class)
public abstract class PlayerMixin implements PlayerBridge {
    @Shadow
    public abstract void shadow$setRespawnPosition(
            @Nullable BlockPos blockPos, boolean forced, boolean verbose);

    @Override
    public void bridge$setRespawnPosition(Location location, boolean forced) {
        this.shadow$setRespawnPosition(
                new BlockPos(location.x(), location.y(), location.z()), forced, false);
    }
}
