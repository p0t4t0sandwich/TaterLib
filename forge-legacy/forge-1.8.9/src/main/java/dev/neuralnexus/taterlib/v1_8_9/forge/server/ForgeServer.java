package dev.neuralnexus.taterlib.v1_8_9.forge.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_8_9.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_8_9.forge.world.ForgeServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.server.MinecraftServer;

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
    return server.getConfigurationManager().playerEntityList.stream()
        .map(ForgePlayer::new)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc} */
  @Override
  public List<ServerWorld> worlds() {
    return Arrays.stream(server.worldServers)
        .map(ForgeServerWorld::new)
        .collect(Collectors.toList());
  }
}
