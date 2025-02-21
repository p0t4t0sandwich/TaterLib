/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta;

/**
 * Enum to identify what "side" code is running on. Explanation borrowed from <a
 * href="https://fabricmc.net/wiki/tutorial:side">Fabric's wiki</a>
 *
 * <table>
 * <caption>Logical vs Physical Client and Server</caption>
 * <tbody><tr><th>x</th><th>Logical Client</th><th>Logical Server</th></tr>
 * <tr><td>Physical Client</td><td>Singleton Always Exists</td><td>Exists when in local save; new instance for each play</td></tr>
 * <tr><td>Physical Server</td><td>Does Not Exist</td><td>Singleton Always Exists</td></tr></tbody>
 * </table>
 *
 * The proxy "side" is used for platforms that do not have a client or server side.
 */
public enum Side {
    PROXY(false, false),
    CLIENT(true, false),
    SERVER(false, true),
    INTEGRATED(true, true);

    private final boolean client;
    private final boolean server;

    Side(boolean client, boolean server) {
        this.client = client;
        this.server = server;
    }

    public boolean isClient() {
        return this.client;
    }

    public boolean isServer() {
        return this.server;
    }

    public boolean is(Side side) {
        return this == side;
    }
}
