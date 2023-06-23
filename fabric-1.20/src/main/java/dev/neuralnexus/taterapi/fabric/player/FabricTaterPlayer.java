package dev.neuralnexus.taterapi.fabric.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Abstracts a Fabric player to a TaterPlayer.
 */
public class FabricTaterPlayer implements TaterPlayer {
    private final PlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Fabric player.
     */
    public FabricTaterPlayer(PlayerEntity player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Fabric player.
     * @param serverName The server name.
     */
    public FabricTaterPlayer(PlayerEntity player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public java.util.UUID getUUID() {
        return player.getUuid();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerName() {
        return serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Text.of(message), false);
    }
}
