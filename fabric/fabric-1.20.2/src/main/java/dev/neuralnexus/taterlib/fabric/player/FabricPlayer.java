package dev.neuralnexus.taterlib.fabric.player;

import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.fabric.inventory.FabricPlayerInventory;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import me.lucko.fabric.api.permissions.v0.Options;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

/** Fabric implementation of {@link Player}. */
public class FabricPlayer extends FabricEntity implements Player {
    private final PlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Fabric player.
     */
    public FabricPlayer(PlayerEntity player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
     * @param player The Fabric player.
     * @param serverName The server name.
     */
    public FabricPlayer(PlayerEntity player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Fabric player
     *
     * @return The Fabric player
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String getIPAddress() {
        return ((ServerPlayerEntity) player).getIp();
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
        player.sendMessage(Text.of(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        String[] channelParts = channel.split(":");
        ServerPlayNetworking.send(
                (ServerPlayerEntity) player,
                new Identifier(channelParts[0], channelParts[1]),
                PacketByteBufs.create().writeByteArray(data));
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new FabricPlayerInventory(player.getInventory());
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((ServerPlayerEntity) player).networkHandler.getLatency();
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayerEntity) player).networkHandler.disconnect(Text.of(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Abstract world information
        ((ServerPlayerEntity) player)
                .setSpawnPoint(
                        World.OVERWORLD,
                        new BlockPos(
                                (int) location.getX(),
                                (int) location.getY(),
                                (int) location.getZ()),
                        0,
                        forced,
                        false);
    }

    /** {@inheritDoc} */
    @Override
    public GameMode getGameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) player).interactionManager.getGameMode().name());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayerEntity) player)
                .interactionManager.changeGameMode(
                        net.minecraft.world.GameMode.byId(gameMode.getId()));
    }

    /** {@inheritDoc} */
    @Override
    public String getPrefix() {
        return Options.get(player, "prefix", "");
    }

    /** {@inheritDoc} */
    @Override
    public String getSuffix() {
        return Options.get(player, "suffix", "");
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissionLevel(permissionLevel);
    }
}
