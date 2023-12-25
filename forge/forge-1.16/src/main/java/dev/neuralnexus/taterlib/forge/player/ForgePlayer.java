package dev.neuralnexus.taterlib.forge.player;

import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.forge.networking.packet.ForgeMessagePacket;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeEntity implements Player {
    private final PlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Forge player.
     */
    public ForgePlayer(PlayerEntity player) {
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
    public ForgePlayer(PlayerEntity player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Forge player
     *
     * @return The Forge player
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String getIPAddress(){
        return ((ServerPlayerEntity) player).getIpAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getServerName() {
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
        player.sendMessage(new StringTextComponent(message), getUniqueId());
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ModMessages.sendPluginMessage(
                new ForgeMessagePacket(new String(data)), channel, (ServerPlayerEntity) player);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((ServerPlayerEntity) player).latency;
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayerEntity) player).connection.disconnect(new StringTextComponent(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayerEntity) player)
                .setRespawnPosition(
                        World.OVERWORLD,
                        new BlockPos(location.getX(), location.getY(), location.getZ()),
                        0.0F,
                        forced,
                        false);
    }

    /** {@inheritDoc} */
    @Override
    public GameMode getGameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) player).gameMode.getGameModeForPlayer().getName());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameMode(net.minecraft.world.GameType.byId(gameMode.getId()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissions(permissionLevel);
    }
}
