package dev.neuralnexus.taterlib.fabric.player;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.fabric.inventory.FabricPlayerInventory;
import me.lucko.fabric.api.permissions.v0.Options;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

/**
 * Fabric implementation of {@link Player}.
 */
public class FabricPlayer extends FabricEntity implements Player {
    private final PlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Fabric player.
     */
    public FabricPlayer(PlayerEntity player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
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
     * @return The Fabric player
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getUniqueId() {
        return player.getUuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServerName() {
        return serverName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(new TranslatableComponent(message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        String[] channelParts = channel.split(":");
        ServerPlayNetworking.send((ServerPlayerEntity) player, new Identifier(channelParts[0], channelParts[1]), PacketByteBufs.create().writeByteArray(data));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventory getInventory() {
        return new FabricPlayerInventory(player.inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayerEntity) player).networkHandler.disconnect(new TranslatableComponent(message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawn(Location location, boolean forced) {
        player.setPlayerSpawn(new BlockPos(location.getX(), location.getY(), location.getZ()), forced);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawn(Location location) {
        setSpawn(location, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        if (!TaterAPIProvider.get().isHooked("luckperms")) return "";
        return Options.get(player, "prefix", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSuffix() {
        if (!TaterAPIProvider.get().isHooked("luckperms")) return "";
        return Options.get(player, "suffix", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(String permission) {
        if (!TaterAPIProvider.get().isHooked("luckperms")) return player.allowsPermissionLevel(4);
        return Permissions.check(player, permission, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.allowsPermissionLevel(permissionLevel);
    }
}
