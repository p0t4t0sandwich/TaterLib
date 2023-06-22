package dev.neuralnexus.taterapi.bukkit;

import dev.neuralnexus.taterapi.bukkit.commands.BukkitTaterAPICommand;
import dev.neuralnexus.taterapi.bukkit.listeners.BukkitPlayerLoginListener;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.neuralnexus.taterapi.common.Utils.getBukkitServerType;

public class BukkitMain extends JavaPlugin {
    public static TaterAPI taterApi;

    // Singleton instance
    private static BukkitMain instance;
    public static BukkitMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Singleton instance
        instance = this;

        getLogger().info("TaterAPI is running on " + getBukkitServerType() + ".");

        // Start TaterAPI
        taterApi = new TaterAPI("plugins", getLogger());
        TaterAPI.start();

        // Register event listener
        getServer().getPluginManager().registerEvents(new BukkitPlayerLoginListener(), this);

        // Register commands
        getCommand(TaterAPICommand.commandName).setExecutor(new BukkitTaterAPICommand());

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
