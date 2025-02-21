/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.fabric.api.minecraft.server.level;

import dev.neuralnexus.taterapi.entity.player.Connection;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;

import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_6)
@Mixin(net.minecraft.server.level.ServerPlayer.class)
@Implements(@Interface(iface = Connection.class, prefix = "connection$", remap = Remap.NONE))
public abstract class ServerPlayer_API_ping {
    @Shadow public ServerGamePacketListenerImpl connection;

    public int connection$ping() {
        return this.connection.latency();
    }
}
