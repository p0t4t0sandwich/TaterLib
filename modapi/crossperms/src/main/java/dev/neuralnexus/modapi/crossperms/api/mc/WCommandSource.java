package dev.neuralnexus.modapi.crossperms.api.mc;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

public class WCommandSource {
    private final Object commandSource;

    private WCommandSource(Object commandSource) {
        this.commandSource = commandSource;
    }

    public static WCommandSource wrap(Object commandSource) {
        return new WCommandSource(commandSource);
    }

    public static Class<?> getClazz() {
        return CrossPerms.instance().store().getClass("CommandSource");
    }

    public static boolean instanceOf(Object obj) {
        return getClazz().isInstance(obj);
    }

    public boolean hasPermission(int permissionLevel) {
        return CrossPerms.instance().store().invokeMethod("CommandSource", "hasPermission", commandSource, permissionLevel);
    }
}
