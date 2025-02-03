/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.server;

import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.sponge.legacy.world.SpongeWorld;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Sponge implementation of {@link Server}. */
public class SpongeServer implements Server {
    private final org.spongepowered.api.Server server;

    public SpongeServer(org.spongepowered.api.Server server) {
        this.server = server;
    }

    @Override
    @SuppressWarnings("JavaReflectionMemberAccess")
    public String brand() {
        // Cast this.server to MinecraftServer, then
        // Reflect to get ((MinecraftServer) server).getServerModName()
        try {
            return (String)
                    Class.forName("net.minecraft.server.MinecraftServer")
                            .getMethod("getServerModName")
                            .invoke(server);
        } catch (Exception e) {
            return "Sponge";
        }
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getOnlinePlayers().stream()
                .map(WrapperRegistry::wrap)
                .map(SimplePlayer.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, UUID> whitelist() {
        // TODO: Implement via mixin
        return Collections.emptyMap();
    }

    @Override
    public Map<String, UUID> playercache() {
        // TODO: Implement via mixin
        return Collections.emptyMap();
    }

    @Override
    public List<ServerWorld> worlds() {
        return server.getWorlds().stream().map(SpongeWorld::new).collect(Collectors.toList());
    }
}
