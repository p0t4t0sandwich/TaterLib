/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit.server;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.world.BukkitServerWorld;

import org.bukkit.OfflinePlayer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link Server}. */
public class BukkitServer implements Server {
    private final org.bukkit.Server server;

    public BukkitServer(org.bukkit.Server server) {
        this.server = server;
    }

    @Override
    public String brand() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
        return Arrays.stream(server.getOnlinePlayers())
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, UUID> whitelist() {
        Map<String, UUID> whitelist = new HashMap<>();
        for (OfflinePlayer player : server.getWhitelistedPlayers()) {
            whitelist.put(player.getName(), new UUID(0, 0));
        }
        return whitelist;
    }

    @Override
    public Map<String, UUID> playercache() {
        // TODO: Find some alternative
        return Collections.emptyMap();
    }

    @Override
    public List<ServerWorld> worlds() {
        return server.getWorlds().stream().map(BukkitServerWorld::new).collect(Collectors.toList());
    }
}
