/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.core.world.entity.player;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V14_4)
@Mixin(Player.class)
public class PlayerMixin implements PlayerBridge {
    @Override
    public void bridge$setRespawnPosition(Location location, boolean forced) {
        ((Player) (Object) this)
                .setRespawnPosition(new BlockPos(location.x(), location.y(), location.z()), forced);
    }
}
