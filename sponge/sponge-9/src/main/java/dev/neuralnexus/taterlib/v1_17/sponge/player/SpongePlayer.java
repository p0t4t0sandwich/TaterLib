/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.sponge.player;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_17.sponge.entity.SpongeLivingEntity;
import dev.neuralnexus.taterlib.v1_17.sponge.inventory.SpongePlayerInventory;
import dev.neuralnexus.taterlib.v1_17.sponge.server.SpongeServer;
import dev.neuralnexus.taterlib.world.Location;

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
public class SpongePlayer extends SpongeLivingEntity implements Player {
    private final org.spongepowered.api.entity.living.player.Player player;

    /**
     * Constructor.
     *
     * @param player The Sponge player.
     */
    public SpongePlayer(org.spongepowered.api.entity.living.player.Player player) {
        super(player);
        this.player = player;
    }

    /**
     * Gets the Sponge player
     *
     * @return The Sponge player
     */
    public org.spongepowered.api.entity.living.player.Player player() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.uniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return ((ServerPlayer) player).connection().address().getAddress().getHostAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.name();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return PlainTextComponentSerializer.plainText().serialize(player.displayName().get());
    }

    /** {@inheritDoc} */
    @Override
    public Server server() {
        return new SpongeServer(Sponge.server());
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
    public PlayerInventory inventory() {
        return new SpongePlayerInventory(player.inventory());
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((ServerPlayer) player).connection().latency();
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
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
    public void allowFlight(boolean allow) {
        player.canFly().set(allow);
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.canFly().get();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.flying().get();
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.flying().set(flying);
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
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
