package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.abstractions.logger.BukkitLogger;
import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.neuralnexus.taterlib.common.Utils.getBukkitServerType;

public class TemplateBukkitPlugin extends JavaPlugin implements TemplatePlugin {
    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new BukkitLogger(getLogger());
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
        return getBukkitServerType();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        return getServer().getVersion();
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
