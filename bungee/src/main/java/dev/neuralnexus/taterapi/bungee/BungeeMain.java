package dev.neuralnexus.taterapi.bungee;

import dev.neuralnexus.taterapi.bungee.commands.BungeeTaterAPICommand;
import dev.neuralnexus.taterapi.bungee.listeners.BungeePlayerLoginListener;
import dev.neuralnexus.taterapi.common.TaterAPI;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeMain extends Plugin {
    public static TaterAPI taterApi;

    // Get server type
    public String getServerType() {
        return "BungeeCord";
    }

    @Override
    public void onEnable() {
        getLogger().info("TaterAPI is running on " + getServerType() + ".");

        // Start TaterAPI
        taterApi = new TaterAPI("plugins", getLogger());
        TaterAPI.start();

        // Register event listener
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerListener(this, new BungeePlayerLoginListener());

        // Register commands
        pluginManager.registerCommand(this, new BungeeTaterAPICommand());

        // Plugin enable message
        getLogger().info("TaterAPI has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin disable message
        getLogger().info("TaterAPI has been disabled!");
        TaterAPI.stop();
    }
}
