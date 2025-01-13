/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

public class WServerOpListEntry {
    private final Object entry;

    private WServerOpListEntry(Object entry) {
        this.entry = entry;
    }

    public static WServerOpListEntry wrap(Object entry) {
        return new WServerOpListEntry(entry);
    }

    public int getLevel() {
        return CrossPerms.instance().store().invokeMethod("ServerOpListEntry", "getLevel", entry);
    }

    public GameProfile getUser() {
        return CrossPerms.instance().store().invokeMethod("ServerOpListEntry", "getUser", entry);
    }
}
