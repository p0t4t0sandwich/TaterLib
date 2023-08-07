package dev.neuralnexus.taterlib.bungee;

import dev.neuralnexus.taterlib.bungee.abstractions.logger.BungeeLogger;
import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import net.md_5.bungee.api.plugin.Plugin;

public class TemplateBungeePlugin extends Plugin implements TemplatePlugin {
    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new BungeeLogger(getLogger());
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
        return "BungeeCord";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        return getProxy().getVersion();
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
