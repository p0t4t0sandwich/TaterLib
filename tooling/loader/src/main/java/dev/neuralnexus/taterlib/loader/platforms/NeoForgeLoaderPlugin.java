package dev.neuralnexus.taterlib.loader.platforms;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/** NeoForge entry point. */
@Mod(TaterLib.Constants.PROJECT_ID)
public class NeoForgeLoaderPlugin {
    private static Loader loader;

    public NeoForgeLoaderPlugin() {
        NeoForge.EVENT_BUS.register(this);
        loader = new TaterLibLoader(this, null, LogUtils.getLogger());
        loader.registerPlugin(getPlugin());
        loader.onInit();
    }

    public static Plugin getPlugin() {
        String version = "";
        switch (MinecraftVersion.minecraftVersion()) {
            case V1_20:
            case V1_20_1:
            case V1_20_2:
            case V1_20_3:
            case V1_20_4:
                version = "." + MinecraftVersion.V1_20_2.getDelimiterString();
                break;
            default:
                System.err.println(
                        "Unsupported Minecraft version: " + MinecraftVersion.minecraftVersion() + "We'll try to load the latest version.");
                version = "." + MinecraftVersion.V1_20_2.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".neoforge.NeoForgeTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (dev.neuralnexus.taterlib.plugin.Plugin)
                    pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
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
