package dev.neuralnexus.taterlib.forge;

import com.mojang.logging.LogUtils;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.abstrations.logger.ForgeLogger;
import dev.neuralnexus.taterlib.forge.commands.ForgeTaterLibCommand;
import dev.neuralnexus.taterlib.forge.listeners.player.ForgePlayerListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * The TaterLib Forge plugin.
 */
@Mod(ForgeTaterLibPlugin.MOD_ID)
public class ForgeTaterLibPlugin extends TemplateForgePlugin implements TaterLibPlugin {
    public static final String MOD_ID = "taterlib";

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new ForgeLogger(LogUtils.getLogger());
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
    public ForgeTaterLibPlugin() {
        // Register server starting/stopping events
        MinecraftForge.EVENT_BUS.register(this);

        // Register player event listeners
        MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());

        // Register commands
        MinecraftForge.EVENT_BUS.register(ForgeTaterLibCommand.class);
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
