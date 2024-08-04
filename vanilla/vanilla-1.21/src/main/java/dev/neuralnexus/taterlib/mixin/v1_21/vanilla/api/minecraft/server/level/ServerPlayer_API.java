/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21.vanilla.api.minecraft.server.level;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.entity.player.Connection;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_21.vanilla.network.VanillaCustomPacketPayload;
import dev.neuralnexus.taterlib.v1_21.vanilla.world.VanillaWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.MOJMAP)
@ReqMCVersion(min = MinecraftVersion.V1_21)
@Mixin(net.minecraft.server.level.ServerPlayer.class)
@Implements({
    @Interface(iface = Connection.class, prefix = "connection$", remap = Remap.NONE),
    @Interface(iface = ServerPlayer.class, prefix = "serverPlayer$", remap = Remap.NONE)
})
public abstract class ServerPlayer_API {
    @Shadow public ServerGamePacketListenerImpl connection;

    @Shadow
    public abstract String shadow$getIpAddress();

    @Shadow
    public abstract void shadow$setRespawnPosition(
            net.minecraft.resources.ResourceKey<Level> dimension,
            @Nullable BlockPos position,
            float angle,
            boolean forced,
            boolean verbose);

    public String connection$ipAddress() {
        return this.shadow$getIpAddress();
    }

    public int connection$ping() {
        return this.connection.latency();
    }

    public void connection$kick(String message) {
        this.connection.disconnect(Component.nullToEmpty(message));
    }

    public void connection$sendPluginMessage(ResourceKey channel, byte[] data) {
        this.connection.send(
                new ClientboundCustomPayloadPacket(new VanillaCustomPacketPayload(channel, data)));
    }

    @SuppressWarnings("resource")
    public void serverPlayer$setSpawn(Location location, boolean forced) {
        this.shadow$setRespawnPosition(
                ((VanillaWorld) location.world()).world().dimension(),
                new BlockPos((int) location.x(), (int) location.y(), (int) location.z()),
                0,
                forced,
                false);
    }
}
