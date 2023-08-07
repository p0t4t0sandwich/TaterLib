package dev.neuralnexus.taterlib.neoforge;

import com.mojang.logging.LogUtils;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.neoforge.abstractions.logger.NeoForgeLogger;
import dev.neuralnexus.taterlib.neoforge.commands.NeoForgeTaterLibCommand;
import dev.neuralnexus.taterlib.neoforge.listeners.player.NeoForgePlayerListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * The TaterLib NeoForge plugin.
 */
@Mod(NeoForgeTaterLibPlugin.MOD_ID)
public class NeoForgeTaterLibPlugin extends TemplateNeoForgePlugin implements TaterLibPlugin {
    public static final String MOD_ID = "taterlib";

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new NeoForgeLogger(LogUtils.getLogger());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        if (ModList.get().isLoaded("luckperms")) {
            useLogger("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook(new LuckPermsHook());
        }
    }

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

    /**
     * Called when the Forge mod is initializing.
     */
    public NeoForgeTaterLibPlugin() {
        // Register server starting/stopping events
        MinecraftForge.EVENT_BUS.register(this);

        // Register player event listeners
        MinecraftForge.EVENT_BUS.register(new NeoForgePlayerListener());

        // Register commands
        MinecraftForge.EVENT_BUS.register(NeoForgeTaterLibCommand.class);
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
