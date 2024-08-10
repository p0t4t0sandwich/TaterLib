/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9.sponge.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_9.sponge.entity.SpongeLivingEntity;
import dev.neuralnexus.taterlib.v1_9.sponge.item.inventory.SpongePlayerInventory;
import dev.neuralnexus.taterlib.v1_9.sponge.server.SpongeServer;
import dev.neuralnexus.taterloader.Loader;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.text.Text;

import java.util.UUID;

/** Sponge implementation of {@link Player}. */
public class SpongePlayer extends SpongeLivingEntity implements Player, ServerPlayer {
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

    @Override
    public UUID uuid() {
        return player.getUniqueId();
    }

    @Override
    public String ipAddress() {
        return player.getConnection().getAddress().getAddress().getHostAddress();
    }

    @Override
    public String name() {
        return player.getName();
    }

    @Override
    public String displayName() {
        return player.getDisplayNameData().displayName().get().toPlain();
    }

    @Override
    public Server server() {
        return new SpongeServer(Sponge.getServer());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Text.of(message));
    }

    @Override
    public void sendPacket(ResourceKey channel, byte[] data) {
        Sponge.getChannelRegistrar()
                .getOrCreateRaw(Loader.instance().plugin(), channel.asString())
                .sendTo(player, (buffer) -> buffer.writeBytes(data));
    }

    @Override
    public PlayerInventory inventory() {
        return new SpongePlayerInventory(player.getInventory().first());
    }

    @Override
    public int ping() {
        return player.getConnection().getLatency();
    }

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

    @Override
    public void allowFlight(boolean allow) {
        player.offer(Keys.CAN_FLY, allow);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public boolean canFly() {
        return player.get(Keys.CAN_FLY).get();
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public boolean isFlying() {
        return player.get(Keys.IS_FLYING).get();
    }

    @Override
    public void setFlying(boolean flying) {
        player.offer(Keys.IS_FLYING, flying);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public GameMode gameMode() {
        return GameMode.fromName(player.get(Keys.GAME_MODE).get().toString());
    }

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
}
