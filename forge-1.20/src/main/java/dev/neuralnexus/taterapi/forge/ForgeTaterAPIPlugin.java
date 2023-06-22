package dev.neuralnexus.taterapi.forge;

import dev.neuralnexus.taterapi.common.TaterAPIPlugin;
import dev.neuralnexus.taterapi.forge.commands.ForgeTaterAPICommand;
import dev.neuralnexus.taterapi.forge.listeners.ForgePlayerLoginListener;
import dev.neuralnexus.taterapi.forge.listeners.server.ForgeServerStartingListener;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * The TaterAPI Forge plugin.
 */
@Mod("taterapi")
public class ForgeTaterAPIPlugin implements TaterAPIPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public Object pluginLogger() {
        return LogUtils.getLogger();
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
    public void registerEventListeners() {
        // Register player event listeners
        MinecraftForge.EVENT_BUS.register(new ForgePlayerLoginListener());

        // Register server event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeServerStartingListener());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        MinecraftForge.EVENT_BUS.register(ForgeTaterAPICommand.class);
    }

    /**
     * Called when the Forge mod is initializing.
     */
    public ForgeTaterAPIPlugin() {
        pluginStart();
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        pluginStop();
    }
}
