package dev.neuralnexus.taterlib.server;

import java.util.Set;

public interface ProxyServer extends SimpleServer {
    /**
     * Gets the servers that are connected to this proxy.
     *
     * @return The servers that are connected to this proxy.
     */
    Set<Server> getServers();
}
