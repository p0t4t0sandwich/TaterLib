/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.mc;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

import java.util.Optional;

/**
 * Represents a CommandSender
 */
public class WCommandSender implements Wrapped{
    private final Object commandSender;

    private WCommandSender(Object commandSender) {
        this.commandSender = commandSender;
    }

    /**
     * Wrap a CommandSender object
     *
     * @param commandSender The CommandSender object to wrap
     * @return The wrapped CommandSender object
     */
    public static WCommandSender wrap(Object commandSender) {
        return new WCommandSender(commandSender);
    }

    @Override
    public Object unwrap() {
        return this.commandSender;
    }

    /**
     * Get the class of the CommandSender object
     *
     * @return The class of the CommandSender object
     */
    public static Class<?> getClazz() {
        return CrossPerms.instance().store().getClass("CommandSender");
    }

    /**
     * Check if an object is an instance of the CommandSender class
     *
     * @param obj The object to check
     * @return True if the object is an instance of the CommandSender class
     */
    public static boolean instanceOf(Object obj) {
        return getClazz().isInstance(obj);
    }

    /**
     * Check if the CommandSender has a permission level
     *
     * @param permissionLevel The permission level to check
     * @return True if the CommandSender has the permission level
     */
    public boolean hasPermission(int permissionLevel) {
        return CrossPerms.instance()
                .store()
                .invokeMethod(
                        "CommandSender", "hasPermission", this.commandSender, permissionLevel);
    }

    /**
     * Get the entity of the CommandSender
     *
     * @return The entity of the CommandSender
     */
    public Optional<Object> getEntity() {
        return Optional.ofNullable(
                CrossPerms.instance()
                        .store()
                        .invokeMethod("CommandSender", "getEntity", this.commandSender));
    }
}
