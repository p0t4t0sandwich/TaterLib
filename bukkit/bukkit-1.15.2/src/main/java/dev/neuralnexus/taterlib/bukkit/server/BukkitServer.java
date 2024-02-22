package dev.neuralnexus.taterlib.bukkit.server;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link Server}. */
public class BukkitServer implements Server {
    private final org.bukkit.Server server;

    public BukkitServer(org.bukkit.Server server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        // Reflect to get ((CraftServer) server).getServer().getServerModName
        try {
            return (String)
                    Class.forName(
                                    "org.bukkit.craftbukkit."
                                            + server.getClass().getPackage().getName()
                                            + ".CraftServer")
                            .getMethod("getServer")
                            .invoke(server)
                            .getClass()
                            .getMethod("getServerModName")
                            .invoke(null);
        } catch (Exception e) {
            return "Bukkit";
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getOnlinePlayers().stream()
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }
}
