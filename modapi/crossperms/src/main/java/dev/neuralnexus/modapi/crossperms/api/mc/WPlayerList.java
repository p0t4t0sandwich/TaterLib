/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.annotations.Range;
import dev.neuralnexus.modapi.metadata.annotations.VersionFeature;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WPlayerList {
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

    public static WPlayerList wrap(Object playerList) {
        return new WPlayerList(playerList);
    }

    @VersionFeature(
            name = "PlayerList.getPlayers",
            compatible = @Range(min = MinecraftVersion.V7, max = MinecraftVersion.V7_10))
    public List<?> getPlayersOld() {
        return CrossPerms.instance().store().getField("PlayerList", "players", playerList);
    }

    @VersionFeature(name = "PlayerList.getPlayers", compatible = @Range(min = MinecraftVersion.V8))
    public List<?> getPlayersNew() {
        return CrossPerms.instance().store().invokeMethod("PlayerList", "getPlayers", playerList);
    }

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

    @VersionFeature(name = "PlayerList.getPlayer", compatible = @Range(min = MinecraftVersion.V8))
    public Optional<WServerPlayer> getPlayerNew(UUID uuid) {
        return Optional.ofNullable(CrossPerms.instance()
                .store()
                .invokeMethod("PlayerList", "getPlayerByUUID", playerList, uuid))
                .map(WServerPlayer::wrap);
    }

    public Optional<WServerPlayer> getPlayer(UUID uuid) {
        if (is7_10) {
            return this.getPlayerOld(uuid);
        }
        return this.getPlayerNew(uuid);
    }

    public Optional<WServerPlayer> getPlayer(String name) {
        Object player =
                CrossPerms.instance()
                        .store()
                        .invokeMethod("PlayerList", "getPlayerByName", playerList, name);
        return null == player ? Optional.empty() : Optional.of(WServerPlayer.wrap(player));
    }

    public Optional<WServerPlayer> getPlayer(GameProfile profile) {
        return this.getPlayer(profile.getId());
    }

    public boolean isOp(GameProfile profile) {
        return CrossPerms.instance()
                .store()
                .invokeMethod("PlayerList", "isOp", playerList, profile);
    }

    public List<WServerOpListEntry> getOps() {
        List<?> opListEntries =
                CrossPerms.instance().store().invokeMethod("PlayerList", "getOps", playerList);

        List<WServerOpListEntry> ops = new ArrayList<>();
        for (Object entry : opListEntries) {
            ops.add(WServerOpListEntry.wrap(entry));
        }
        return Collections.unmodifiableList(ops);
    }

    public static boolean hasPermissionLevel(GameProfile profile, int permissionLevel) {
        List<WServerOpListEntry> ops = WMinecraftServer.getPlayerList().getOps();
        for (WServerOpListEntry entry : ops) {
            if (entry.getUser().getId().equals(profile.getId())) {
                return entry.getLevel() >= permissionLevel;
            }
        }
        return false;
    }

    public static boolean hasPermissionLevel(UUID uuid, int permissionLevel) {
        Optional<WServerPlayer> player = WMinecraftServer.getPlayerList().getPlayer(uuid);
        if (is13_up) {
            return player.map(p -> p.hasPermission(permissionLevel)).orElse(false);
        }
        return player.map(WServerPlayer::getGameProfile)
                .map(gameProfile -> hasPermissionLevel(gameProfile, permissionLevel))
                .orElse(false);
    }

    public static boolean hasPermissionLevel(String name, int permissionLevel) {
        Optional<WServerPlayer> player = WMinecraftServer.getPlayerList().getPlayer(name);
        if (is13_up) {
            return player.map(p -> p.hasPermission(permissionLevel)).orElse(false);
        }
        return player.map(WServerPlayer::getGameProfile)
                .map(gameProfile -> hasPermissionLevel(gameProfile, permissionLevel))
                .orElse(false);
    }
}
