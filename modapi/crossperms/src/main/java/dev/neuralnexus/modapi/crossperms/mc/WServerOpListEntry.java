/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.taterapi.Wrapped;

public class WServerOpListEntry implements Wrapped<Object> {
    private final Object entry;

    private WServerOpListEntry(Object entry) {
        this.entry = entry;
    }

    /**
     * Wrap a ServerOpListEntry object
     *
     * @param entry The ServerOpListEntry object to wrap
     * @return The wrapped ServerOpListEntry object
     */
    public static WServerOpListEntry wrap(Object entry) {
        return new WServerOpListEntry(entry);
    }

    @Override
    public Object unwrap() {
        return this.entry;
    }

    /**
     * Get the level of the ServerOpListEntry
     *
     * @return The level of the ServerOpListEntry
     */
    public int getLevel() {
        return CrossPerms.instance().store().invokeMethod("ServerOpListEntry", "getLevel", entry);
    }

    /**
     * Get the user of the ServerOpListEntry
     *
     * @return The user of the ServerOpListEntry
     */
    public GameProfile getUser() {
        return CrossPerms.instance().store().invokeMethod("ServerOpListEntry", "getUser", entry);
    }
}
