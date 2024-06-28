/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_3.forge.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_16_3.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_16_3.forge.world.ForgeServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;

import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link Server}. */
public class ForgeServer implements Server {
    private final MinecraftServer server;

    public ForgeServer(MinecraftServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        return server.getServerModName();
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getPlayerList().getPlayers().stream()
                .map(ForgePlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        server.getAllLevels().forEach(world -> worlds.add(new ForgeServerWorld(world)));
        return worlds;
    }
}
