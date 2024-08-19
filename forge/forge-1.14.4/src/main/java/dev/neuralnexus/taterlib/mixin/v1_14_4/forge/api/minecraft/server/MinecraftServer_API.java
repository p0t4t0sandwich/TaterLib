/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.api.minecraft.server;

import com.mojang.authlib.GameProfile;
import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.mixin.v1_14_4.forge.bridge.server.players.GameProfileCacheBridge;
import dev.neuralnexus.taterlib.mixin.v1_14_4.forge.bridge.server.players.StoredUserEntryBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaServerWorld;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.server.players.PlayerList;

import net.minecraft.server.players.UserWhiteListEntry;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V1_14, max = MinecraftVersion.V1_14_4)
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Remap.NONE))
public abstract class MinecraftServer_API implements GameProfileCacheBridge, StoredUserEntryBridge {
    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract PlayerList shadow$getPlayerList();

    @Shadow
    public abstract GameProfileCache shadow$getProfileCache();

    @Shadow
    public abstract Iterable<ServerLevel> shadow$getAllLevels();

    public String server$brand() {
        return this.shadow$getServerModName();
    }

    public List<SimplePlayer> server$onlinePlayers() {
        return this.shadow$getPlayerList().getPlayers().stream()
                .map(SimplePlayer.class::cast)
                .collect(Collectors.toList());
    }

    public Map<String, UUID> server$whitelist() {
        Map<String, UUID> whitelist = new HashMap<>();
        for (UserWhiteListEntry user : this.shadow$getPlayerList().getWhiteList().getEntries()) {
            GameProfile profile = this.bridge$getUser(user);
            whitelist.put(profile.getName(), profile.getId());
        }
        return whitelist;
    }

    public Map<String, UUID> server$playercache() {
        Map<String, UUID> cache = new HashMap<>();
        for (GameProfile profile : this.bridge$getProfilesbyName(this.shadow$getProfileCache()).values()) {
            cache.put(profile.getName(), profile.getId());
        }
        return cache;
    }

    public List<ServerWorld> server$worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        this.shadow$getAllLevels().forEach(world -> worlds.add(new VanillaServerWorld(world)));
        return worlds;
    }
}
