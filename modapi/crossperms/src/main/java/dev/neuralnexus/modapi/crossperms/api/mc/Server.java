/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
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
        return new PlayerList(
                CrossPerms.instance()
                        .store()
                        .invokeMethod("MinecraftServer", "getPlayerList", server));
    }
}
