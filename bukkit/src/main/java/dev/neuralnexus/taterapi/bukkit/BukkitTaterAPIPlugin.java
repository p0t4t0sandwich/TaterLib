package dev.neuralnexus.taterapi.bukkit;

import dev.neuralnexus.taterapi.bukkit.commands.BukkitTaterAPICommand;
import dev.neuralnexus.taterapi.bukkit.listeners.BukkitPlayerLoginListener;
import dev.neuralnexus.taterapi.bukkit.listeners.BukkitPlayerLogoutListener;
import dev.neuralnexus.taterapi.common.TaterAPIPlugin;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.neuralnexus.taterapi.common.Utils.getBukkitServerType;

/**
 * The TaterAPI Bukkit plugin.
 */
public class BukkitTaterAPIPlugin extends JavaPlugin implements TaterAPIPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public Object pluginLogger() {
        return getLogger();
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
    public void registerEventListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BukkitPlayerLoginListener(), this);
        pluginManager.registerEvents(new BukkitPlayerLogoutListener(), this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        getCommand(TaterAPICommand.commandName).setExecutor(new BukkitTaterAPICommand());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onEnable() {
        pluginStart();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onDisable() {
        pluginStop();
    }
}
