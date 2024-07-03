/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.plugin.Plugin;

/** Locates and returns the core TaterLib plugin. */
public class TaterPluginResolver {
    public static Plugin bukkit(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.B1_7, MinecraftVersion.B1_7_3)) {
            version = "." + MinecraftVersion.B1_7_3.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_2_1, MinecraftVersion.V1_2_5)) {
            version = "." + MinecraftVersion.V1_2_5.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_6_1, MinecraftVersion.V1_6_4)) {
            version = "." + MinecraftVersion.V1_6_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_8.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_13_2)) {
            version = "." + MinecraftVersion.V1_13_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_14, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".bukkit.BukkitTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (Plugin) pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }

    public static Plugin bungeeCord(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_4_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_4_7.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_8.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_12.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".bungee.BungeeTaterLibPlugin";
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

    public static Plugin fabric(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_9.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = "." + MinecraftVersion.V1_9_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_10_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_14_4)) {
            version = "." + MinecraftVersion.V1_14.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_16.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = "." + MinecraftVersion.V1_17.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_18.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else if (mcv.is(MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_21.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".fabric.FabricTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (Plugin) pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }

    public static Plugin forge(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_9.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = "." + MinecraftVersion.V1_9_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_10_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_13_2)) {
            version = "." + MinecraftVersion.V1_13_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_14, MinecraftVersion.V1_14_4)) {
            version = "." + MinecraftVersion.V1_14_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15_1.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_16_3.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = "." + MinecraftVersion.V1_17_1.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_18.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20_4)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20_5, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20_6.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20_6.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".forge.ForgeTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (Plugin) pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }

    public static Plugin legacyForge(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".forge.ForgeTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (Plugin) pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }

    public static Plugin neoForge(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20_2.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
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

    public static Plugin sponge7(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_9.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_12.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".sponge.SpongeTaterLibPlugin";
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

    public static Plugin sponge8(Loader loader) {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_13.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_17.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".sponge.SpongeTaterLibPlugin";
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

    public static Plugin velocity(Loader loader) {
        String pluginClassName = "dev.neuralnexus.taterlib.velocity.v3_3_0.VelocityTaterLibPlugin";
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
}
