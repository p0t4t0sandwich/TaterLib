/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.api.minecraft.server;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_7_10.fabric.entity.player.FabricPlayer;
import dev.neuralnexus.taterlib.v1_7_10.fabric.world.FabricServerWorld;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ReqPlatform(Platform.FABRIC)
@ReqMCVersion(min = MinecraftVersion.V1_7_2, max = MinecraftVersion.V1_7_10)
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Interface.Remap.NONE))
public abstract class MinecraftServerAPI {
    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract PlayerManager shadow$getPlayerManager();

    @Shadow public net.minecraft.server.world.ServerWorld[] worlds;

    public String server$brand() {
        return shadow$getServerModName();
    }

    @SuppressWarnings("unchecked")
    public List<SimplePlayer> server$onlinePlayers() {
        return ((List<PlayerEntity>) shadow$getPlayerManager().players)
                .stream().map(FabricPlayer::new).collect(Collectors.toList());
    }

    public List<ServerWorld> server$worlds() {
        return Arrays.stream(worlds).map(FabricServerWorld::new).collect(Collectors.toList());
    }
}
