/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.annotations.Range;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.annotations.VersionFeature;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.perms.CrossPerms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Represents a PlayerList */
public class WPlayerList implements Wrapped<Object> {
    private static final boolean is7_10 =
            MetaAPI.instance().version().isInRange(MinecraftVersions.V7, MinecraftVersions.V7_10);
    public static final boolean is13_up =
            MetaAPI.instance()
                    .version()
                    .isInRange(MinecraftVersions.V13, MinecraftVersions.UNKNOWN);
    private final Object playerList;

    private WPlayerList(Object playerList) {
        this.playerList = playerList;
    }

    /**
     * Wrap a PlayerList object
     *
     * @param playerList The PlayerList object to wrap
     * @return The wrapped PlayerList object
     */
    public static WPlayerList wrap(Object playerList) {
        return new WPlayerList(playerList);
    }

    @Override
    public Object unwrap() {
        return this.playerList;
    }

    /**
     * Get the list of players in the PlayerList
     *
     * @return The list of players in the PlayerList
     */
    @VersionFeature(
            name = "PlayerList.getPlayers",
            compatible = @Range(min = MinecraftVersion.V7, max = MinecraftVersion.V7_10))
    public List<?> getPlayersOld() {
        return CrossPerms.instance().store().getField("PlayerList", "players", playerList);
    }

    /**
     * Get the list of players in the PlayerList
     *
     * @return The list of players in the PlayerList
     */
    @VersionFeature(name = "PlayerList.getPlayers", compatible = @Range(min = MinecraftVersion.V8))
    public List<?> getPlayersNew() {
        return CrossPerms.instance().store().invokeMethod("PlayerList", "getPlayers", playerList);
    }

    /**
     * Get the players in the PlayerList
     *
     * @return The players in the PlayerList
     */
    public List<WServerPlayer> players() {
        List<?> playerObjects;
        if (is7_10) {
            playerObjects = this.getPlayersOld();
        } else {
            playerObjects = this.getPlayersNew();
        }
        List<WServerPlayer> players = new ArrayList<>();
        for (Object player : playerObjects) {
            players.add(WServerPlayer.wrap(player));
        }
        return Collections.unmodifiableList(players);
    }

    /**
     * Get the player with the specified UUID
     *
     * @param uuid The UUID of the player
     * @return The player with the specified UUID
     */
    @VersionFeature(
            name = "PlayerList.getPlayer",
            compatible = @Range(min = MinecraftVersion.V7, max = MinecraftVersion.V7_10))
    public Optional<WServerPlayer> getPlayerOld(UUID uuid) {
        List<WServerPlayer> players = this.players();
        for (WServerPlayer player : players) {
            if (player.getUUID().equals(uuid)) {
                return Optional.of(WServerPlayer.wrap(player));
            }
        }
        return Optional.empty();
    }

    /**
     * Get the player with the specified UUID
     *
     * @param uuid The UUID of the player
     * @return The player with the specified UUID
     */
    @VersionFeature(name = "PlayerList.getPlayer", compatible = @Range(min = MinecraftVersion.V8))
    public Optional<WServerPlayer> getPlayerNew(UUID uuid) {
        return Optional.ofNullable(
                        CrossPerms.instance()
                                .store()
                                .invokeMethod("PlayerList", "getPlayerByUUID", playerList, uuid))
                .map(WServerPlayer::wrap);
    }

    /**
     * Get the player with the specified UUID
     *
     * @param uuid The UUID of the player
     * @return The player with the specified UUID
     */
    public Optional<WServerPlayer> getPlayer(UUID uuid) {
        if (is7_10) {
            return this.getPlayerOld(uuid);
        }
        return this.getPlayerNew(uuid);
    }

    /**
     * Get the player with the specified name
     *
     * @param name The name of the player
     * @return The player with the specified name
     */
    public Optional<WServerPlayer> getPlayer(String name) {
        Object player =
                CrossPerms.instance()
                        .store()
                        .invokeMethod("PlayerList", "getPlayerByName", playerList, name);
        return null == player ? Optional.empty() : Optional.of(WServerPlayer.wrap(player));
    }

    /**
     * Get the player with the specified GameProfile
     *
     * @param profile The GameProfile of the player
     * @return The player with the specified GameProfile
     */
    public Optional<WServerPlayer> getPlayer(GameProfile profile) {
        return this.getPlayer(profile.getId());
    }

    /**
     * Check if a player is an operator
     *
     * @param profile The GameProfile of the player
     * @return True if the player is an operator
     */
    public boolean isOp(GameProfile profile) {
        return CrossPerms.instance()
                .store()
                .invokeMethod("PlayerList", "isOp", playerList, profile);
    }

    /**
     * Get the list of operators in the PlayerList
     *
     * @return The list of operators in the PlayerList
     */
    public List<WServerOpListEntry> getOps() {
        List<?> opListEntries =
                CrossPerms.instance().store().invokeMethod("PlayerList", "getOps", playerList);

        List<WServerOpListEntry> ops = new ArrayList<>();
        for (Object entry : opListEntries) {
            ops.add(WServerOpListEntry.wrap(entry));
        }
        return Collections.unmodifiableList(ops);
    }

    /**
     * Check if a player has a permission level
     *
     * @param profile The GameProfile of the player
     * @param permissionLevel The permission level to check
     * @return True if the player has the permission level
     */
    public static boolean hasPermissionLevel(GameProfile profile, int permissionLevel) {
        List<WServerOpListEntry> ops = WMinecraftServer.getPlayerList().getOps();
        for (WServerOpListEntry entry : ops) {
            if (entry.getUser().getId().equals(profile.getId())) {
                return entry.getLevel() >= permissionLevel;
            }
        }
        return false;
    }
}
