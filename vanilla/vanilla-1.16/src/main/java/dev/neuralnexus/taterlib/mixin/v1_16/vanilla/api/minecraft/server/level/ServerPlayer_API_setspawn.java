/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16.vanilla.api.minecraft.server.level;

import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16.vanilla.world.VanillaWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V16, max = MinecraftVersion.V16_1)
@Mixin(net.minecraft.server.level.ServerPlayer.class)
@Implements(@Interface(iface = ServerPlayer.class, prefix = "serverPlayer$", remap = Remap.NONE))
public abstract class ServerPlayer_API_setspawn {
    @Shadow
    public abstract void shadow$setRespawnPosition(
            net.minecraft.resources.ResourceKey<Level> dimension,
            @Nullable BlockPos position,
            boolean forced,
            boolean verbose);

    @SuppressWarnings("resource")
    public void serverPlayer$setSpawn(Location location, boolean forced) {
        this.shadow$setRespawnPosition(
                ((VanillaWorld) location.world()).unwrap().dimension(),
                new BlockPos(location.x(), location.y(), location.z()),
                forced,
                false);
    }
}
