/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21.vanilla.api.minecraft.client;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.SimpleServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.ServerData;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

@ReqMappings(Mappings.MOJMAP)
@ReqMCVersion(min = MinecraftVersion.V1_21)
@Mixin(Minecraft.class)
@Implements(@Interface(iface = SimpleServer.class, prefix = "server$", remap = Remap.NONE))
public abstract class Minecraft_API {
    @Shadow
    @Nullable public abstract ClientPacketListener shadow$getConnection();

    public String server$brand() {
        if (this.shadow$getConnection() == null) return "Local";
        String brand = this.shadow$getConnection().serverBrand();
        return brand == null ? "Local" : brand;
    }

    public List<SimplePlayer> server$onlinePlayers() {
        return this.shadow$getConnection().getOnlinePlayers().stream()
                .map(PlayerInfo::getProfile)
                .map(SimplePlayer.class::cast)
                .collect(Collectors.toList());
    }

    void server$broadcastMessage(String message) {
        this.shadow$getConnection().sendChat(message);
    }
}
