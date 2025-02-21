/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_11_2.forge.api.minecraft.server;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V11, max = MinecraftVersion.V11_2)
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Interface.Remap.NONE))
public abstract class MinecraftServerAPI {
    @Shadow public WorldServer[] worlds;

    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract PlayerList shadow$getPlayerList();

    public String server$brand() {
        return shadow$getServerModName();
    }

    public List<User> server$onlinePlayers() {
        return shadow$getPlayerList().getPlayers().stream()
                .map(ForgePlayer::new)
                .collect(Collectors.toList());
    }

    public List<ServerWorld> server$worlds() {
        return Arrays.stream(this.worlds).map(ForgeServerWorld::new).collect(Collectors.toList());
    }
}
