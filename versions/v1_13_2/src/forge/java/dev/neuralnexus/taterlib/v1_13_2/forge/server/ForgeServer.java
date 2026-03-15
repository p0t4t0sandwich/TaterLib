/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.server;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.mc.server.MinecraftServer;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.mc.server.players.UserWhiteListEntry;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_13_2.forge.world.ForgeServerWorld;

import net.minecraft.server.management.PlayerProfileCache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Forge implementation of {@link Server}. */
public class ForgeServer implements Server {
    private final net.minecraft.server.MinecraftServer server;

    public ForgeServer(net.minecraft.server.MinecraftServer server) {
        this.server = server;
    }

    @Override
    public String brand() {
        return server.getServerModName();
    }

    @Override
    public List<User> players() {
        return server.getPlayerList().getPlayers().stream()
                .map(ForgePlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<NameAndId> whitelist() {
        return MinecraftServer.getPlayerList().getWhiteList().getEntries().stream()
                .map(UserWhiteListEntry::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, UUID> playercache() {
        Map<String, UUID> cache = new HashMap<>();

        // Reflect to get PlayerProfileCache.usernameToProfileEntryMap (field_152661_c)
        PlayerProfileCache playerCache = server.getPlayerProfileCache();
        Map<String, ?> info = null;
        try {
            info =
                    (Map<String, ?>)
                            playerCache.getClass().getField("field_152661_c").get(playerCache);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            TaterAPI.logger()
                    .error("Failed to get usernameToProfileEntryMap from PlayerProfileCache", e);
        }
        for (Object i : info.values()) {
            // Reflect to get GameProfileCache$GameProfileInfo#getProfile() (func_152668_a)
            try {
                Method method = i.getClass().getMethod("func_152668_a");
                method.setAccessible(true);
                GameProfile profile = (GameProfile) method.invoke(i);
                cache.put(profile.getName(), profile.getId());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                TaterAPI.logger().error("Failed to get GameProfile from GameProfileCache", e);
            }
        }
        return cache;
    }

    @Override
    public List<ServerWorld> worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        server.func_212370_w().forEach(world -> worlds.add(new ForgeServerWorld(world)));
        return worlds;
    }
}
