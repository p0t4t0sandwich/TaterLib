/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_11.sponge.player;

import dev.neuralnexus.taterapi.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.player.GameMode;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_11.sponge.entity.SpongeLivingEntity;
import dev.neuralnexus.taterlib.v1_11.sponge.inventory.SpongePlayerInventory;
import dev.neuralnexus.taterlib.v1_11.sponge.server.SpongeServer;
import dev.neuralnexus.taterloader.Loader;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelRegistrar;
import org.spongepowered.api.text.Text;

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
        return player.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return player.getConnection().getAddress().getAddress().getHostAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.getName();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> displayName() {
        return player.getDisplayNameData().displayName().get().toPlain();
    }

    /** {@inheritDoc} */
    @Override
    public Server server() {
        return new SpongeServer(Sponge.getServer());
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Text.of(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ChannelRegistrar channelRegistrar = Sponge.getChannelRegistrar();
        ChannelBinding.RawDataChannel channelBinding =
                channelRegistrar.getOrCreateRaw(Loader.instance().plugin(), channel);
        channelBinding.sendTo(player, (buffer) -> buffer.writeBytes(data));
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new SpongePlayerInventory(player.getInventory().first());
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return player.getConnection().getLatency();
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        player.kick(Text.of(message));
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
        player.offer(Keys.CAN_FLY, allow);
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.get(Keys.CAN_FLY).get();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.get(Keys.IS_FLYING).get();
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.offer(Keys.IS_FLYING, flying);
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
                player.offer(Keys.GAME_MODE, GameModes.CREATIVE);
                break;
            case SURVIVAL:
                player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
                break;
            case ADVENTURE:
                player.offer(Keys.GAME_MODE, GameModes.ADVENTURE);
                break;
            case SPECTATOR:
                player.offer(Keys.GAME_MODE, GameModes.SPECTATOR);
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
