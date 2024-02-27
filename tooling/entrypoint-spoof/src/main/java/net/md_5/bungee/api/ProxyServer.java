package net.md_5.bungee.api;

/** Fake BungeeCord ProxyServer class. */
public abstract class ProxyServer {
    private static ProxyServer instance;

    public static ProxyServer getInstance() {
        return instance;
    }
}
