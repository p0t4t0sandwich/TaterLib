package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;

public class TemplateSpongePlugin implements TemplatePlugin {
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
    public String pluginConfigPath() {
        return "config";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerType() {
        return "Sponge";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        PluginContainer apiContainer = Sponge.getPlatform().getContainer(Platform.Component.API);
        return apiContainer.getVersion().toString();
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
