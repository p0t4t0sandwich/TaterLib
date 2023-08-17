package dev.neuralnexus.taterlib.neoforge.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * Abstracts a Forge player to an AbstractPlayer.
 */
public class NeoForgePlayer implements AbstractPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Forge player.
     */
    public NeoForgePlayer(Player player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public NeoForgePlayer(Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public java.util.UUID getUUID() {
        return player.getUUID();
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
        player.displayClientMessage(Component.empty().append(message), false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return new NeoForgePlayerInventory(player.getInventory());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayer) player).connection.disconnect(Component.empty().append(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        if (!LuckPermsHook.isHooked()) return player.hasPermissions(4);
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        return luckPermsHook.playerHasPermission(getUUID(), permission);
    }
}
