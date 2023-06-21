package dev.neuralnexus.taterapi.bungee;

import dev.neuralnexus.taterapi.bungee.commands.BungeeTemplateCommand;
import dev.neuralnexus.taterapi.bungee.listeners.BungeePlayerLoginListener;
import dev.neuralnexus.taterapi.common.TaterAPI;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {
    public TaterAPI taterApi;

    // Get server type
    public String getServerType() {
        return "BungeeCord";
    }

    // Singleton instance
    private static BungeeMain instance;
    public static BungeeMain getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        // Singleton instance
        instance = this;

        getLogger().info("Template is running on " + getServerType() + ".");

        // Start
        taterApi = new TaterAPI("plugins", getLogger());
        taterApi.start();

        // Register event listener
        getProxy().getPluginManager().registerListener(this, new BungeePlayerLoginListener());

        // Register commands
        getProxy().getPluginManager().registerCommand(this, new BungeeTemplateCommand());

        // Plugin enable message
        getLogger().info("Template has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin disable message
        getLogger().info("Template has been disabled!");
    }
}
