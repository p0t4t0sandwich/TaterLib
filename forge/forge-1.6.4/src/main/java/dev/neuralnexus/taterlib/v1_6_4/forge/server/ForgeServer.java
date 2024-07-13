/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.server;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_6_4.forge.world.ForgeServerWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link Server}. */
public class ForgeServer implements Server {
    private final MinecraftServer server;

    public ForgeServer(MinecraftServer server) {
        this.server = server;
    }

    @Override
    public String brand() {
        return server.getServerModName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SimplePlayer> onlinePlayers() {
        return ((List<EntityPlayer>) server.getConfigurationManager().playerEntityList)
                .stream().map(ForgePlayer::new).collect(Collectors.toList());
    }

    @Override
    public List<ServerWorld> worlds() {
        return Arrays.stream(server.worldServers)
                .map(ForgeServerWorld::new)
                .collect(Collectors.toList());
    }
}
