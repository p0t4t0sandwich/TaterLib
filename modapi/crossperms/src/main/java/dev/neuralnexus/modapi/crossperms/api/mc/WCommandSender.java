/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.mc;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

public class WCommandSender {
    private final Object commandSender;

    private WCommandSender(Object commandSender) {
        this.commandSender = commandSender;
    }

    public static WCommandSender wrap(Object commandSender) {
        return new WCommandSender(commandSender);
    }

    public static Class<?> getClazz() {
        return CrossPerms.instance().store().getClass("CommandSender");
    }

    public static boolean instanceOf(Object obj) {
        return getClazz().isInstance(obj);
    }

    public boolean hasPermission(int permissionLevel) {
        return CrossPerms.instance()
                .store()
                .invokeMethod(
                        "CommandSender", "hasPermission", this.commandSender, permissionLevel);
    }
}
