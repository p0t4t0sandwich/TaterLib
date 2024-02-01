package dev.neuralnexus.taterlib.loader.platforms;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.v1_20_2.neoforge.NeoForgeTaterLibPlugin;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/** NeoForge entry point. */
@Mod(TaterLib.Constants.PROJECT_ID)
public class NeoForgeLoaderPlugin {
    private final Loader loader;

    public NeoForgeLoaderPlugin() {
        NeoForge.EVENT_BUS.register(this);
        loader = new TaterLibLoader(this, LogUtils.getLogger());
        loader.registerPlugin(new NeoForgeTaterLibPlugin());
        loader.onInit();
        loader.onEnable();
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        loader.onEnable();
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        loader.onDisable();
    }
}
