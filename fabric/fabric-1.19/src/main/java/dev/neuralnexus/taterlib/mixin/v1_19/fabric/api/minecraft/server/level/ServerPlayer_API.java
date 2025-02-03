/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.fabric.api.minecraft.server.level;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.entity.player.Connection;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.world.VanillaWorld;

import io.netty.buffer.Unpooled;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V19, max = MinecraftVersion.V19_4)
@Mixin(net.minecraft.server.level.ServerPlayer.class)
@Implements({
    @Interface(iface = Connection.class, prefix = "connection$", remap = Remap.NONE),
    @Interface(iface = ServerPlayer.class, prefix = "serverPlayer$", remap = Remap.NONE)
})
public abstract class ServerPlayer_API {
    @Shadow public int latency;

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
        return this.latency;
    }

    public void connection$kick(String message) {
        this.connection.disconnect(Component.nullToEmpty(message));
    }

    public void connection$sendPacket(ResourceKey channel, byte[] data) {
        ResourceLocation id = (ResourceLocation) channel;
        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
        connection.send(new ClientboundCustomPayloadPacket(id, byteBuf));
    }

    public void serverPlayer$setSpawn(Location location, boolean forced) {
        this.shadow$setRespawnPosition(
                ((VanillaWorld) location.world()).unwrap().dimension(),
                new BlockPos(location.x(), location.y(), location.z()),
                0.0F,
                forced,
                false);
    }
}
