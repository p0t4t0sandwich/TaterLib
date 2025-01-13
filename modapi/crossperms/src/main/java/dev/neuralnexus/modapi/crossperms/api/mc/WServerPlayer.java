/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

import java.util.UUID;

public class WServerPlayer {
    private final Object player;

    private WServerPlayer(Object player) {
        this.player = player;
    }

    public static WServerPlayer wrap(Object player) {
        return new WServerPlayer(player);
    }

    public GameProfile getGameProfile() {
        return CrossPerms.instance().store().invokeMethod("ServerPlayer", "getGameProfile", player);
    }

    public String getName() {
        return this.getGameProfile().getName();
    }

    public UUID getUUID() {
        return this.getGameProfile().getId();
    }

    public boolean hasPermission(int permissionLevel) {
        return CrossPerms.instance().store().invokeMethod("CommandSource", "hasPermission", player, permissionLevel);
    }
}
