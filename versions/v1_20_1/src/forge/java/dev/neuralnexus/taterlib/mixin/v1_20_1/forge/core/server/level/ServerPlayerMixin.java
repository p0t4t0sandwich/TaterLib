/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_1.forge.core.server.level;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.level.ServerPlayerBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V20, max = MinecraftVersion.V20_1)
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin implements ServerPlayerBridge {
    @Shadow public ServerGamePacketListenerImpl connection;

    @Shadow
    public abstract void shadow$setRespawnPosition(
            ResourceKey<Level> dimension,
            @Nullable BlockPos position,
            float angle,
            boolean forced,
            boolean verbose);

    @Override
    public void bridge$kick(String message) {
        this.connection.disconnect(Component.nullToEmpty(message));
    }

    @Override
    @SuppressWarnings("resource")
    public void bridge$setRespawnPosition(Location location, boolean forced) {
        this.shadow$setRespawnPosition(
                ((VanillaWorld) location.world()).unwrap().dimension(),
                BlockPos.containing(location.x(), location.y(), location.z()),
                location.yaw(),
                forced,
                false);
    }
}
