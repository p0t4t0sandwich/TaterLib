package dev.neuralnexus.taterlib.server;

import java.util.List;

/** Represents a proxy server. */
public interface ProxyServer extends SimpleServer {
    /**
     * Gets the servers that are connected to this proxy.
     *
     * @return The servers that are connected to this proxy.
     */
    List<Server> servers();
}
