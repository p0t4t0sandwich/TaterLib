/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.perms.CrossPerms;

import java.util.UUID;

public class WServerPlayer implements Wrapped<Object> {
    private final Object player;

    private WServerPlayer(Object player) {
        this.player = player;
    }

    /**
     * Wrap a ServerPlayer object
     *
     * @param player The ServerPlayer object to wrap
     * @return The wrapped ServerPlayer object
     */
    public static WServerPlayer wrap(Object player) {
        return new WServerPlayer(player);
    }

    @Override
    public Object unwrap() {
        return this.player;
    }

    /**
     * Get the GameProfile of the ServerPlayer
     *
     * @return The GameProfile of the ServerPlayer
     */
    public GameProfile getGameProfile() {
        return CrossPerms.instance().store().invokeMethod("ServerPlayer", "getGameProfile", player);
    }

    /**
     * Get the name of the ServerPlayer
     *
     * @return The name of the ServerPlayer
     */
    public String getName() {
        return this.getGameProfile().getName();
    }

    /**
     * Get the UUID of the ServerPlayer
     *
     * @return The UUID of the ServerPlayer
     */
    public UUID getUUID() {
        return this.getGameProfile().getId();
    }

    /**
     * Check if the ServerPlayer has a permission level
     *
     * @param permissionLevel The permission level to check
     * @return True if the ServerPlayer has the permission level
     */
    public boolean hasPermission(int permissionLevel) {
        return CrossPerms.instance()
                .store()
                .invokeMethod("ServerPlayer", "hasPermissions", player, permissionLevel);
    }

    /**
     * Get the class of the ServerPlayer object
     *
     * @return The class of the ServerPlayer object
     */
    public static Class<?> getClazz() {
        return CrossPerms.instance().store().getClass("ServerPlayer");
    }

    /**
     * Check if an object is an instance of the ServerPlayer class
     *
     * @param obj The object to check
     * @return True if the object is an instance of the ServerPlayer class
     */
    public static boolean instanceOf(Object obj) {
        return getClazz().isInstance(obj);
    }
}
