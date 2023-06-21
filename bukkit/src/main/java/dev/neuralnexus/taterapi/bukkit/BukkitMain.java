package dev.neuralnexus.taterapi.bukkit;

import dev.neuralnexus.taterapi.bukkit.commands.BukkitTemplateCommand;
import dev.neuralnexus.taterapi.bukkit.listeners.BukkitPlayerLoginListener;
import dev.neuralnexus.taterapi.common.TaterAPI;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.neuralnexus.taterapi.common.Utils.*;

public class BukkitMain extends JavaPlugin {
    public TaterAPI taterApi;

    // Singleton instance
    private static BukkitMain instance;
    public static BukkitMain getInstance() {
        return instance;
    }

    public String getServerType() {
        if (isMagma()) {
            return "Magma";
        } else if (isMohist()) {
            return "Mohist";
        } else if (isArclight()) {
            return "Arclight";
        } else if (isFolia()) {
            return "Folia";
        } else if (isPaper()) {
            return "Paper";
        } else if (isSpigot()) {
            return "Spigot";
        } else if (isCraftBukkit()) {
            return "CraftBukkit";
        } else {
            return "Unknown";
        }
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
        getServer().getPluginManager().registerEvents(new BukkitPlayerLoginListener(), this);

        // Register commands
        getCommand("template").setExecutor(new BukkitTemplateCommand());

        // Plugin enable message
        getLogger().info("Template has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin disable message
        getLogger().info("Template has been disabled!");
    }
}
