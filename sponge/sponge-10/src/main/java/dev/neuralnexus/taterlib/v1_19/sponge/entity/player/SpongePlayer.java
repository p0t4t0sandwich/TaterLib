/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.sponge.entity.player;

import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_19.sponge.entity.SpongeLivingEntity;
import dev.neuralnexus.taterlib.v1_19.sponge.item.inventory.SpongePlayerInventory;
import dev.neuralnexus.taterlib.v1_19.sponge.server.SpongeServer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
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

    @Override
    public UUID uuid() {
        return player.uniqueId();
    }

    @Override
    public String ipAddress() {
        return ((ServerPlayer) player).connection().address().getAddress().getHostAddress();
    }

    @Override
    public String name() {
        return player.name();
    }

    @Override
    public String displayName() {
        return PlainTextComponentSerializer.plainText().serialize(player.displayName().get());
    }

    @Override
    public Server server() {
        return new SpongeServer(Sponge.server());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.text(message));
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        Sponge.channelManager()
                .ofType((org.spongepowered.api.ResourceKey) (Object) channel, RawDataChannel.class)
                .play()
                .sendTo((ServerPlayer) player, (buffer) -> buffer.writeBytes(data));
    }

    @Override
    public PlayerInventory inventory() {
        return new SpongePlayerInventory(player.inventory());
    }

    @Override
    public int ping() {
        return ((ServerPlayer) player).connection().latency();
    }

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

    @Override
    public void allowFlight(boolean allow) {
        player.canFly().set(allow);
    }

    @Override
    public boolean canFly() {
        return player.canFly().get();
    }

    @Override
    public boolean isFlying() {
        return player.flying().get();
    }

    @Override
    public void setFlying(boolean flying) {
        player.flying().set(flying);
    }

    @Override
    public GameMode gameMode() {
        return GameMode.fromName(player.get(Keys.GAME_MODE).get().toString());
    }

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

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
