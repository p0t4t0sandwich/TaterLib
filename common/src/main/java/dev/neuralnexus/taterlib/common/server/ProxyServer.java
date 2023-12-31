package dev.neuralnexus.taterlib.common.server;

import java.util.Set;

public interface ProxyServer extends Server {
    /**
     * Gets the servers that are connected to this proxy.
     * @return The servers that are connected to this proxy.
     */
    Set<Server> getServers();
}
