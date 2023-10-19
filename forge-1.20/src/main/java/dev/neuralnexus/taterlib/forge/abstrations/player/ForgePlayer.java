package dev.neuralnexus.taterlib.forge.abstrations.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.abstrations.util.ForgeConversions;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.forge.networking.packet.ForgeMessagePacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.UUID;

/**
 * Abstracts a Forge player to an AbstractPlayer.
 */
public class ForgePlayer implements AbstractPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Forge player.
     */
    public ForgePlayer(Player player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public ForgePlayer(Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Forge player
     * @return The Forge player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
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
    public Position getPosition() {
        return ForgeConversions.positionFromVector(player.position());
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
        player.displayClientMessage(Component.nullToEmpty(message), false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ModMessages.sendPluginMessage(new ForgeMessagePacket(new String(data)), channel, (ServerPlayer) player);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return new ForgePlayerInventory(player.getInventory());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayer) player).connection.disconnect(Component.nullToEmpty(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Position position) {
        ((ServerPlayer) player).setRespawnPosition(Level.OVERWORLD, ForgeConversions.locationFromPosition(position), 0, true, false);
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
