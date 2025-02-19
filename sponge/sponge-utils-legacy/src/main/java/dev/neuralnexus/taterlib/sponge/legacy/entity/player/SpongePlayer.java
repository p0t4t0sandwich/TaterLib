/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.sponge.legacy.entity.SpongeLivingEntity;
import dev.neuralnexus.taterlib.sponge.legacy.item.inventory.SpongePlayerInventory;
import dev.neuralnexus.taterlib.sponge.legacy.server.SpongeServer;

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

    @Override
    public org.spongepowered.api.entity.living.player.Player unwrap() {
        return this.player;
    }

    @Override
    public UUID uuid() {
        return this.player.getUniqueId();
    }

    @Override
    public String ipAddress() {
        return this.player.getConnection().getAddress().getAddress().getHostAddress();
    }

    @Override
    public String name() {
        return this.player.getName();
    }

    @Override
    public String displayName() {
        return this.player.getDisplayNameData().displayName().get().toPlain();
    }

    @Override
    public Server server() {
        return SpongeServer.instance();
    }

    @Override
    public void sendMessage(String message) {
        this.player.sendMessage(Text.of(message));
    }

    @Override
    public void sendPacket(ResourceKey channel, byte[] data) {
        Sponge.getChannelRegistrar()
                .getOrCreateRaw(Loader.instance().plugin(), channel.asString())
                .sendTo(this.player, (buffer) -> buffer.writeBytes(data));
    }

    @Override
    public PlayerInventory inventory() {
        return new SpongePlayerInventory(player.getInventory().first());
    }

    @Override
    public int ping() {
        return this.player.getConnection().getLatency();
    }

    @Override
    public void kick(String message) {
        this.player.kick(Text.of(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Find method to set spawn
        //        this.player.get(Keys.RESPAWN_LOCATIONS).get().entrySet().stream().filter(entry ->
        // entry.getKey().getExtent().equals(location.getWorld().toSponge())).forEach(entry ->
        // entry.setValue(location.toSponge()));
        //        player.setBedSpawnLocation(location.toSponge(), forced);
    }

    @Override
    public void allowFlight(boolean allow) {
        this.player.offer(Keys.CAN_FLY, allow);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public boolean canFly() {
        return this.player.get(Keys.CAN_FLY).get();
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public boolean isFlying() {
        return this.player.get(Keys.IS_FLYING).get();
    }

    @Override
    public void setFlying(boolean flying) {
        this.player.offer(Keys.IS_FLYING, flying);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public GameMode gameMode() {
        return GameMode.fromName(this.player.get(Keys.GAME_MODE).get().toString());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        switch (gameMode) {
            case CREATIVE:
                this.player.offer(Keys.GAME_MODE, GameModes.CREATIVE);
                break;
            case SURVIVAL:
                this.player.offer(Keys.GAME_MODE, GameModes.SURVIVAL);
                break;
            case ADVENTURE:
                this.player.offer(Keys.GAME_MODE, GameModes.ADVENTURE);
                break;
            case SPECTATOR:
                this.player.offer(Keys.GAME_MODE, GameModes.SPECTATOR);
                break;
        }
    }
}
