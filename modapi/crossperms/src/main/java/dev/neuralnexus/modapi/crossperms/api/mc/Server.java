package dev.neuralnexus.modapi.crossperms.api.mc;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import org.jetbrains.annotations.ApiStatus;

public class Server {
    private static Object server;

    @ApiStatus.Internal
    public static Object setServer(Object server) {
        return Server.server = server;
    }

    public static PlayerList getPlayerList() {
        return new PlayerList(CrossPerms.instance().store().invokeMethod("MinecraftServer", "getPlayerList", server));
    }
}
