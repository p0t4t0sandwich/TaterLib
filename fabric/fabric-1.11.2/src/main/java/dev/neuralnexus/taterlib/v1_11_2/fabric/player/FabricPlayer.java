package dev.neuralnexus.taterlib.v1_11_2.fabric.player;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_11_2.fabric.entity.FabricLivingEntity;
import dev.neuralnexus.taterlib.v1_11_2.fabric.inventory.FabricPlayerInventory;
import dev.neuralnexus.taterlib.v1_11_2.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.world.Location;

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

    /**
     * Constructor.
     *
     * @param player The Fabric player.
     */
    public FabricPlayer(PlayerEntity player) {
        super(player);
        this.player = player;
    }

    /**
     * Gets the Fabric player
     *
     * @return The Fabric player
     */
    public PlayerEntity player() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) player).getIp();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.getName().asFormattedString();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return player.getCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public Server server() {
        return new FabricServer(player.getMinecraftServer());
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
    public PlayerInventory inventory() {
        return new FabricPlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((ServerPlayerEntity) player).ping;
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) player).networkHandler.onDisconnected(new TranslatableText(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Dimension aware spawn setting
        player.setPlayerSpawn(new BlockPos(location.x(), location.y(), location.z()), forced);
    }

    /** {@inheritDoc} */
    @Override
    public void allowFlight(boolean allow) {
        player.abilities.allowFlying = allow;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.abilities.allowFlying;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.abilities.flying;
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.abilities.flying = flying;
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) player).interactionManager.getGameMode().name());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayerEntity) player)
                .interactionManager.setGameMode(
                        net.minecraft.world.GameMode.setGameModeWithId(gameMode.id()));
    }

    /** {@inheritDoc} */
    @Override
    public String prefix() {
        // TODO: Find a way to get LP prefixes/suffixes
        //        return Options.get(player, "prefix", "");
        return "";
    }

    /** {@inheritDoc} */
    @Override
    public String suffix() {
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
