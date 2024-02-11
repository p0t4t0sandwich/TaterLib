package dev.neuralnexus.taterlib.fabric.player;

import dev.neuralnexus.taterlib.fabric.entity.FabricLivingEntity;
import dev.neuralnexus.taterlib.fabric.inventory.FabricPlayerInventory;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import net.legacyfabric.fabric.api.networking.v1.PacketByteBufs;
import net.legacyfabric.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

/** Fabric implementation of {@link Player}. */
public class FabricPlayer extends FabricLivingEntity implements Player {
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
        return player.getName().asFormattedString();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getCustomName();
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
        player.sendMessage(new TranslatableText(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ServerPlayNetworking.send(
                (ServerPlayerEntity) player, channel, PacketByteBufs.create().writeByteArray(data));
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new FabricPlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((ServerPlayerEntity) player).ping;
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayerEntity) player).networkHandler.onDisconnected(new TranslatableText(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        player.setPlayerSpawn(
                new BlockPos(location.getX(), location.getY(), location.getZ()), forced);
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
                .interactionManager.setGameMode(
                        net.minecraft.world.level.LevelInfo.GameMode.byId(gameMode.getId()));
    }

    /** {@inheritDoc} */
    @Override
    public String getPrefix() {
        // TODO: Find a way to get LP prefixes/suffixes
        //        return Options.get(player, "prefix", "");
        return "";
    }

    /** {@inheritDoc} */
    @Override
    public String getSuffix() {
        // TODO: Find a way to get LP prefixes/suffixes
        //        return Options.get(player, "suffix", "");
        return "";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
