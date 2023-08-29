package dev.neuralnexus.taterlib.forge;

import dev.neuralnexus.taterlib.common.TemplatePlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

public class TemplateForgePlugin implements TemplatePlugin {
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
        return "Forge";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerVersion() {
        String mcVersion = ForgeVersion.mcVersion;
        String forgeVersion = ForgeVersion.majorVersion + "." + ForgeVersion.minorVersion + "." + ForgeVersion.revisionVersion + "." + ForgeVersion.buildVersion;
        return mcVersion + "-" + forgeVersion;
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
