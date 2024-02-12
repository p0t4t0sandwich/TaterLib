package dev.neuralnexus.taterlib.forge.player;

import dev.neuralnexus.taterlib.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.forge.networking.packet.ForgeMessagePacket;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeLivingEntity implements Player {
    private final net.minecraft.world.entity.player.Player player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Forge player.
     */
    public ForgePlayer(net.minecraft.world.entity.player.Player player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public ForgePlayer(net.minecraft.world.entity.player.Player player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Forge player
     *
     * @return The Forge player
     */
    public net.minecraft.world.entity.player.Player player() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return ((ServerPlayer) player).getIpAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.getName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return player.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String serverName() {
        return serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.displayClientMessage(Component.nullToEmpty(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ModMessages.sendPluginMessage(
                new ForgeMessagePacket(new String(data)), channel, (ServerPlayer) player);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(player.getInventory());
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((ServerPlayer) player).latency;
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        ((ServerPlayer) player).connection.disconnect(Component.nullToEmpty(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayer) player)
                .setRespawnPosition(
                        Level.OVERWORLD,
                        new BlockPos(location.x(), location.y(), location.z()),
                        0.0F,
                        forced,
                        false);
    }

    /** {@inheritDoc} */
    @Override
    public void allowFlight(boolean allow) {
        player.getAbilities().mayfly = allow;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.getAbilities().mayfly;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.getAbilities().flying;
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.getAbilities().flying = flying;
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
        return GameMode.fromName(((ServerPlayer) player).gameMode.getGameModeForPlayer().getName());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayer) player).setGameMode(net.minecraft.world.level.GameType.byId(gameMode.id()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissions(permissionLevel);
    }
}
