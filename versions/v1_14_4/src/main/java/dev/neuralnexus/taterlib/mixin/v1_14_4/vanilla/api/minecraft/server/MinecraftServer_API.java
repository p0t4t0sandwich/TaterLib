/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.api.minecraft.server;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.players.GameProfileCacheBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.players.UserWhitelistEntryBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaServerWorld;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Remap.NONE))
public abstract class MinecraftServer_API implements GameProfileCacheBridge {
    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract PlayerList shadow$getPlayerList();

    @Shadow
    public abstract Iterable<ServerLevel> shadow$getAllLevels();

    public String server$brand() {
        return this.shadow$getServerModName();
    }

    public List<User> server$onlinePlayers() {
        return this.shadow$getPlayerList().getPlayers().stream()
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    public Map<String, UUID> server$whitelist() {
        return this.shadow$getPlayerList().getWhiteList().getEntries().stream()
                .map(UserWhitelistEntryBridge.class::cast)
                .map(UserWhitelistEntryBridge::bridge$getUser)
                .collect(Collectors.toMap(GameProfile::getName, GameProfile::getId));
    }

    public Map<String, UUID> server$playercache() {
        return this.bridge$getProfilesbyName().values().stream()
                .collect(Collectors.toMap(GameProfile::getName, GameProfile::getId));
    }

    public List<ServerWorld> server$worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        this.shadow$getAllLevels().forEach(world -> worlds.add(new VanillaServerWorld(world)));
        return worlds;
    }
}
