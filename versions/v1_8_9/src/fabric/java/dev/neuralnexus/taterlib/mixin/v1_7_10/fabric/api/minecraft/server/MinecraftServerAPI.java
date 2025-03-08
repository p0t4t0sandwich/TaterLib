/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.api.minecraft.server;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.WrappedServerWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.WorldServer;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Interface.Remap.NONE))
public abstract class MinecraftServerAPI {
    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract ServerConfigurationManager shadow$getConfigurationManager();

    @Shadow public WorldServer[] worldServers;

    public String server$brand() {
        return this.shadow$getServerModName();
    }

    @SuppressWarnings("unchecked")
    public List<User> server$players() {
        return ((List<EntityPlayer>) this.shadow$getConfigurationManager().playerEntityList)
                .stream().map(WrappedPlayer::new).collect(Collectors.toList());
    }

    public List<ServerWorld> server$worlds() {
        return Arrays.stream(this.worldServers)
                .map(WrappedServerWorld::new)
                .collect(Collectors.toList());
    }
}
