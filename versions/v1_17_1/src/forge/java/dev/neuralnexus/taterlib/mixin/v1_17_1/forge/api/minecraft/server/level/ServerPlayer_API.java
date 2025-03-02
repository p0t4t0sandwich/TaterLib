/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.api.minecraft.server.level;

import dev.neuralnexus.taterapi.entity.player.Connection;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.level.ServerPlayerBridge;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V18_2)
@Mixin(net.minecraft.server.level.ServerPlayer.class)
@Implements({
    @Interface(iface = Connection.class, prefix = "connection$", remap = Remap.NONE),
    @Interface(iface = ServerPlayer.class, prefix = "serverPlayer$", remap = Remap.NONE)
})
public abstract class ServerPlayer_API implements ServerPlayerBridge {
    @Shadow
    public abstract String shadow$getIpAddress();

    public String connection$ipAddress() {
        return this.shadow$getIpAddress();
    }

    public int connection$ping() {
        return this.bridge$ping();
    }

    public void connection$kick(String message) {
        this.bridge$kick(message);
    }

    public void connection$sendPacket(ResourceKey channel, byte[] data) {
        this.bridge$sendPacket(channel, data);
    }

    public void serverPlayer$setSpawn(Location location, boolean forced) {
        this.bridge$setRespawnPosition(location, forced);
    }
}
