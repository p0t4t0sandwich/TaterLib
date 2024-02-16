package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import org.slf4j.Logger;

/** Velocity entry point. */
// @Plugin(
//        id = TaterLib.Constants.PROJECT_ID,
//        name = TaterLib.Constants.PROJECT_NAME,
//        version = TaterLib.Constants.PROJECT_VERSION,
//        authors = TaterLib.Constants.PROJECT_AUTHORS,
//        description = TaterLib.Constants.PROJECT_DESCRIPTION,
//        url = TaterLib.Constants.PROJECT_URL)
public class VelocityLoaderPlugin {
    private static Loader loader;

    @Inject
    public VelocityLoaderPlugin(ProxyServer server, Logger logger) {
        loader = new TaterLibLoader(this, server, logger);
        loader.registerPlugin(getPlugin());
        loader.onInit();
    }

    public static Plugin getPlugin() {
        //        String version = "Unsupported";
        //        switch (MinecraftVersion.getMinecraftVersion()) {
        //            case V1_20:
        //            case V1_20_1:
        //            case V1_20_2:
        //            case V1_20_3:
        //            case V1_20_4:
        //                version = MinecraftVersion.V1_20.getDelimiterString();
        //                break;
        //            default:
        //                System.err.println(
        //                        "Unsupported Minecraft version: " +
        // MinecraftVersion.getMinecraftVersion());
        //        }
        String pluginClassName = "dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin";
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

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        loader.onEnable();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        loader.onDisable();
    }
}
