package dev.neuralnexus.taterlib.sponge.player;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.inventory.SpongePlayerInventory;
import dev.neuralnexus.taterlib.utils.Location;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.network.channel.ChannelManager;
import org.spongepowered.api.network.channel.raw.RawDataChannel;

import java.util.UUID;

/** Sponge implementation of {@link Player}. */
public class SpongePlayer extends SpongeEntity implements Player {
    private final org.spongepowered.api.entity.living.player.Player player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Sponge player.
     */
    public SpongePlayer(org.spongepowered.api.entity.living.player.Player player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
     * @param player The Sponge player.
     * @param serverName The name of the server the player is on.
     */
    public SpongePlayer(
            org.spongepowered.api.entity.living.player.Player player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Sponge player
     *
     * @return The Sponge player
     */
    public org.spongepowered.api.entity.living.player.Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.uniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.name();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return PlainTextComponentSerializer.plainText().serialize(player.displayName().get());
    }

    /** {@inheritDoc} */
    @Override
    public String getServerName() {
        return serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.text(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ChannelManager channelManager = Sponge.channelManager();
        String[] channelParts = channel.split(":");
        channelManager
                .ofType(ResourceKey.of(channelParts[0], channelParts[1]), RawDataChannel.class)
                .play()
                .sendTo((ServerPlayer) player, (buffer) -> buffer.writeBytes(data));
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new SpongePlayerInventory(player.inventory());
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((ServerPlayer) player).connection().latency();
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayer) player).kick(Component.text(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Find method to set spawn
        //        player.get(Keys.RESPAWN_LOCATIONS).get().entrySet().stream().filter(entry ->
        // entry.getKey().getExtent().equals(location.getWorld().toSponge())).forEach(entry ->
        // entry.setValue(location.toSponge()));
        //        player.setBedSpawnLocation(location.toSponge(), forced);
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location) {
        setSpawn(location, false);
    }

    /** {@inheritDoc} */
    @Override
    public GameMode getGameMode() {
        return GameMode.fromName(player.get(Keys.GAME_MODE).get().toString());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        switch (gameMode) {
            case CREATIVE:
                player.offer(Keys.GAME_MODE, GameModes.CREATIVE.get());
                break;
            case SURVIVAL:
                player.offer(Keys.GAME_MODE, GameModes.SURVIVAL.get());
                break;
            case ADVENTURE:
                player.offer(Keys.GAME_MODE, GameModes.ADVENTURE.get());
                break;
            case SPECTATOR:
                player.offer(Keys.GAME_MODE, GameModes.SPECTATOR.get());
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
