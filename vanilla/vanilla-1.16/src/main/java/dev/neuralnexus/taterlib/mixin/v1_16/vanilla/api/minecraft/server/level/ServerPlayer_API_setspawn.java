/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16.vanilla.api.minecraft.server.level;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
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

@ReqMappings(Mappings.MOJMAP)
@ReqMCVersion(min = MinecraftVersion.V16, max = MinecraftVersion.V16_1)
@Mixin(net.minecraft.server.level.ServerPlayer.class)
@Implements(@Interface(iface = ServerPlayer.class, prefix = "serverPlayer$", remap = Remap.NONE))
@SuppressWarnings({"unused", "UnusedMixin"})
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
                ((VanillaWorld) location.world()).world().dimension(),
                new BlockPos(location.x(), location.y(), location.z()),
                forced,
                false);
    }
}
