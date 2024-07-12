/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterloader;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.server.Server;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** Utility class for reflection. */
public class TaterReflectUtil {
    public static final String TL_PACKAGE = "dev.neuralnexus.taterlib";
    public static final Map<Platform, String> packageNames = new HashMap<>();

    static {
        resolvePackageNames(Loader.instance());
    }

    public static Optional<String> getClass(String clazz) {
        return getClass(clazz, TaterAPIProvider.primaryPlatform());
    }

    public static Optional<String> getClass(String clazz, Platform platform) {
        String packageName = packageNames.get(platform);
        if (packageName == null) {
            return Optional.empty();
        }
        return Optional.of(packageName + "." + clazz);
    }

    public static Optional<String> getClass(
            String clazz, MinecraftVersion version, Platform platform) {
        String packageName = packageNames.get(platform);
        if (packageName == null) {
            return Optional.empty();
        }
        return Optional.of(packageName + "." + version.getDelimiterString() + "." + clazz);
    }

    public static Optional<String> getRelocatedClass(String clazz, Platform relocatingPlatform) {
        String packageName = packageNames.get(Platform.VANILLA);
        if (packageName == null) {
            return Optional.empty();
        }
        String path = "";
        if (relocatingPlatform == Platform.FORGE
                && MinecraftVersion.get().isOlderThan(MinecraftVersion.V1_20_6)) {
            path = ".forge.";
        } else if (relocatingPlatform == Platform.FABRIC) {
            path = ".fabric.";
        }
        return Optional.of(packageName + path + clazz);
    }

    public static Optional<String> getRelocatedClass(String clazz) {
        return getRelocatedClass(clazz, TaterAPIProvider.primaryPlatform());
    }

    public static void resolvePackageNames(Loader loader) {
        MinecraftVersion mcv = loader.minecraftVersion();
        packageNames.put(Platform.BUKKIT, bukkit(mcv));
        packageNames.put(Platform.BUNGEECORD, bungeeCord(mcv));
        packageNames.put(Platform.FABRIC, fabric(mcv));
        packageNames.put(Platform.FORGE, forge(mcv));
        packageNames.put(Platform.NEOFORGE, neoForge(mcv));
        packageNames.put(Platform.SPONGE, sponge(mcv));
        packageNames.put(Platform.VELOCITY, velocity(mcv));
        packageNames.put(Platform.VANILLA, vanilla(mcv));
    }

    public static String bukkit(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.B1_7, MinecraftVersion.B1_7_3)) {
            version = MinecraftVersion.B1_7_3;
        } else if (mcv.isInRange(MinecraftVersion.V1_2_1, MinecraftVersion.V1_2_5)) {
            version = MinecraftVersion.V1_2_5;
        } else if (mcv.isInRange(MinecraftVersion.V1_6_1, MinecraftVersion.V1_6_4)) {
            version = MinecraftVersion.V1_6_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = MinecraftVersion.V1_7_10;
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = MinecraftVersion.V1_8_8;
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_13_2)) {
            version = MinecraftVersion.V1_13_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_14, MinecraftVersion.V1_15_2)) {
            version = MinecraftVersion.V1_15_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_20;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_20;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".bukkit";
    }

    public static String bungeeCord(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.V1_4_2, MinecraftVersion.V1_7_10)) {
            version = MinecraftVersion.V1_4_7;
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_11_2)) {
            version = MinecraftVersion.V1_8;
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_15_2)) {
            version = MinecraftVersion.V1_12;
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_20;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_20;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".bungee";
    }

    public static String fabric(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = MinecraftVersion.V1_7_10;
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = MinecraftVersion.V1_8_9;
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = MinecraftVersion.V1_9_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = MinecraftVersion.V1_10_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = MinecraftVersion.V1_11_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = MinecraftVersion.V1_12_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_14_4)) {
            version = MinecraftVersion.V1_14;
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = MinecraftVersion.V1_15;
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = MinecraftVersion.V1_16;
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = MinecraftVersion.V1_17;
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = MinecraftVersion.V1_18;
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = MinecraftVersion.V1_19;
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20_6)) {
            version = MinecraftVersion.V1_20;
        } else if (mcv.is(MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_21;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_20;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".fabric";
    }

    public static String forge(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.V1_6_1, MinecraftVersion.V1_6_4)) {
            version = MinecraftVersion.V1_6_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = MinecraftVersion.V1_7_10;
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = MinecraftVersion.V1_8_9;
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = MinecraftVersion.V1_9_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = MinecraftVersion.V1_10_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = MinecraftVersion.V1_11_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = MinecraftVersion.V1_12_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_13_2)) {
            version = MinecraftVersion.V1_13_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_14, MinecraftVersion.V1_14_4)) {
            version = MinecraftVersion.V1_14_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = MinecraftVersion.V1_15_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = MinecraftVersion.V1_16_3;
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = MinecraftVersion.V1_17_1;
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = MinecraftVersion.V1_18;
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = MinecraftVersion.V1_19;
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20_4)) {
            version = MinecraftVersion.V1_20;
        } else if (mcv.isInRange(MinecraftVersion.V1_20_5, MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_20_6;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_20_6;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".forge";
    }

    public static String neoForge(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_20_2;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_20_2;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".neoforge";
    }

    public static String sponge(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = MinecraftVersion.V1_8;
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_10_2)) {
            version = MinecraftVersion.V1_9;
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = MinecraftVersion.V1_11;
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = MinecraftVersion.V1_12;
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_16_5)) {
            version = MinecraftVersion.V1_13;
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_18_2)) {
            version = MinecraftVersion.V1_17;
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = MinecraftVersion.V1_19;
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_20;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_20;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".sponge";
    }

    public static String velocity(MinecraftVersion mcv) {
        return TL_PACKAGE + ".velocity.v3_3_0";
    }

    public static String vanilla(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersion.B1_7, MinecraftVersion.B1_7_3)) {
            version = MinecraftVersion.B1_7_3;
        } else if (mcv.isInRange(MinecraftVersion.V1_2_1, MinecraftVersion.V1_2_5)) {
            version = MinecraftVersion.V1_2_5;
        } else if (mcv.isInRange(MinecraftVersion.V1_6_1, MinecraftVersion.V1_6_4)) {
            version = MinecraftVersion.V1_6_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = MinecraftVersion.V1_7_10;
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = MinecraftVersion.V1_8_9;
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = MinecraftVersion.V1_9_4;
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = MinecraftVersion.V1_10_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = MinecraftVersion.V1_11_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = MinecraftVersion.V1_12_2;
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_14_4)) {
            version = MinecraftVersion.V1_14;
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = MinecraftVersion.V1_15;
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = MinecraftVersion.V1_16;
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = MinecraftVersion.V1_17;
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = MinecraftVersion.V1_18;
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = MinecraftVersion.V1_19;
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20_4)) {
            version = MinecraftVersion.V1_20;
        } else if (mcv.is(MinecraftVersion.V1_21)) {
            version = MinecraftVersion.V1_21;
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = MinecraftVersion.V1_21;
        }
        return TL_PACKAGE + "." + version.getDelimiterString() + ".vanilla";
    }

    public static <T> T newInstance(String clazz) {
        return newInstance(clazz, TaterAPIProvider.primaryPlatform());
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String clazz, Platform platform, Object... args) {
        try {
            Class<?> objectClass = Class.forName(getClass(clazz, platform).get());
            return (T) objectClass.getConstructor().newInstance(args);
        } catch (ClassNotFoundException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Server newVanillaServer() {
        return TaterReflectUtil.getRelocatedClass("VanillaServer")
                .map(
                        className -> {
                            try {
                                return (Server)
                                        Class.forName(className).getMethod("instance").invoke(null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                .get();
    }
}
