package dev.neuralnexus.taterlib.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;

public class TemplateVelocityPlugin implements TemplatePlugin {
    public static ProxyServer proxyServer;

    /**
     * Get the proxy server.
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String pluginConfigPath() {
        return "plugins";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerType() {
        return "Velocity";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        return proxyServer.getVersion().getVersion();
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {}

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {}

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {}
}
