/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_11_2.forge.api.minecraft.server;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_11_2.forge.entity.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_11_2.forge.world.ForgeServerWorld;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.WorldServer;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V1_11, max = MinecraftVersion.V1_11_2)
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Interface.Remap.NONE))
public abstract class MinecraftServerAPI {
    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract PlayerList shadow$getPlayerList();

    @Shadow
    public abstract Iterable<WorldServer> shadow$getAllLevels();

    public String server$brand() {
        return shadow$getServerModName();
    }

    public List<SimplePlayer> server$onlinePlayers() {
        return shadow$getPlayerList().getPlayers().stream()
                .map(ForgePlayer::new)
                .collect(Collectors.toList());
    }

    public List<ServerWorld> server$worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        shadow$getAllLevels().forEach(world -> worlds.add(new ForgeServerWorld(world)));
        return worlds;
    }
}
