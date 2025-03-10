/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** Utility class for reflection. */
public class TaterReflectUtil {
    public static final String TL_PACKAGE = "dev.neuralnexus.taterlib";
    public static final Map<Platform, String> packageNames = new HashMap<>();
    private static final Logger logger = Logger.create("TaterReflectUtil");

    static {
        resolvePackageNames();
    }

    public static Optional<String> getClass(String clazz) {
        return getClass(clazz, MetaAPI.instance().primaryPlatform());
    }

    public static Optional<String> getClass(String clazz, Platform platform) {
        String packageName = packageNames.get(platform);
        if (null == packageName) {
            return Optional.empty();
        }
        return Optional.of(packageName + "." + clazz);
    }

    public static Optional<String> getClass(
            String clazz, MinecraftVersion version, Platform platform) {
        String packageName = packageNames.get(platform);
        if (null == packageName) {
            return Optional.empty();
        }
        return Optional.of(packageName + "." + version.getPathString() + "." + clazz);
    }

    public static Optional<String> getRelocatedClass(String clazz, Platform relocatingPlatform) {
        String packageName = packageNames.get(Platforms.VANILLA);
        if (null == packageName) {
            return Optional.empty();
        }
        String path = ".";
        if (relocatingPlatform == Platforms.FORGE
                && MetaAPI.instance().version().isOlderThan(MinecraftVersions.V20_6)) {
            path += "forge.";
        } else if (relocatingPlatform == Platforms.FABRIC) {
            path += "fabric.";
        }
        return Optional.of(packageName + path + clazz);
    }

    public static Optional<String> getRelocatedClass(String clazz) {
        return getRelocatedClass(clazz, MetaAPI.instance().primaryPlatform());
    }

    public static void resolvePackageNames() {
        MinecraftVersion mcv = MetaAPI.instance().version();
        packageNames.put(Platforms.BUKKIT, bukkit(mcv));
        packageNames.put(Platforms.BUNGEECORD, bungeeCord(mcv));
        packageNames.put(Platforms.FABRIC, fabric(mcv));
        packageNames.put(Platforms.FORGE, forge(mcv));
        packageNames.put(Platforms.NEOFORGE, neoForge(mcv));
        packageNames.put(Platforms.SPONGE, sponge(mcv));
        packageNames.put(Platforms.VELOCITY, velocity(mcv));
        packageNames.put(Platforms.VANILLA, vanilla(mcv));
    }

    public static String bukkit(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersions.B1_7, MinecraftVersions.B1_7_3)) {
            return TL_PACKAGE + ".b1_7_3.bukkit";
        } else if (mcv.isInRange(MinecraftVersions.V2_1, MinecraftVersions.V2_5)) {
            version = MinecraftVersions.V2_5;
        } else if (mcv.isInRange(MinecraftVersions.V6_1, MinecraftVersions.V6_4)) {
            version = MinecraftVersions.V6_4;
        } else if (mcv.isInRange(MinecraftVersions.V7_2, MinecraftVersions.V7_10)) {
            version = MinecraftVersions.V7_10;
        } else if (mcv.isInRange(MinecraftVersions.V8, MinecraftVersions.V8_9)) {
            version = MinecraftVersions.V8_8;
        } else if (mcv.isInRange(MinecraftVersions.V9, MinecraftVersions.V13_2)) {
            version = MinecraftVersions.V13_2;
        } else if (mcv.isInRange(MinecraftVersions.V14, MinecraftVersions.V15_2)) {
            version = MinecraftVersions.V15_2;
        } else if (mcv.isInRange(MinecraftVersions.V16, MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V20;
        } else {
            version = MinecraftVersions.V20;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".bukkit";
    }

    public static String bungeeCord(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersions.V4_2, MinecraftVersions.V7_10)) {
            version = MinecraftVersions.V4_7;
        } else if (mcv.isInRange(MinecraftVersions.V8, MinecraftVersions.V11_2)) {
            version = MinecraftVersions.V8;
        } else if (mcv.isInRange(MinecraftVersions.V12, MinecraftVersions.V15_2)) {
            version = MinecraftVersions.V12;
        } else if (mcv.isInRange(MinecraftVersions.V16, MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V20;
        } else {
            version = MinecraftVersions.V20;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".bungee";
    }

    public static String fabric(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersions.V7_2, MinecraftVersions.V8_9)) {
            version = MinecraftVersions.V8_9;
        } else if (mcv.isInRange(MinecraftVersions.V9, MinecraftVersions.V9_4)) {
            version = MinecraftVersions.V9_4;
        } else if (mcv.isInRange(MinecraftVersions.V10, MinecraftVersions.V10_2)) {
            version = MinecraftVersions.V10_2;
        } else if (mcv.isInRange(MinecraftVersions.V11, MinecraftVersions.V11_2)) {
            version = MinecraftVersions.V11_2;
        } else if (mcv.isInRange(MinecraftVersions.V12, MinecraftVersions.V12_2)) {
            version = MinecraftVersions.V12_2;
        } else if (mcv.isInRange(MinecraftVersions.V13, MinecraftVersions.V14_4)) {
            version = MinecraftVersions.V14_4;
        } else if (mcv.isInRange(MinecraftVersions.V15, MinecraftVersions.V15_2)) {
            version = MinecraftVersions.V15_2;
        } else if (mcv.isInRange(MinecraftVersions.V16, MinecraftVersions.V16_5)) {
            version = MinecraftVersions.V16_1;
        } else if (mcv.isInRange(MinecraftVersions.V17, MinecraftVersions.V18_2)) {
            version = MinecraftVersions.V17_1;
        } else if (mcv.isInRange(MinecraftVersions.V19, MinecraftVersions.V19_4)) {
            version = MinecraftVersions.V19;
        } else if (mcv.isInRange(MinecraftVersions.V20, MinecraftVersions.V20_4)) {
            version = MinecraftVersions.V20_2;
        } else if (mcv.isInRange(MinecraftVersions.V20_5, MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V21_4;
        } else {
            version = MinecraftVersions.V21_4;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".fabric";
    }

    public static String forge(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersions.V6_1, MinecraftVersions.V6_4)) {
            version = MinecraftVersions.V6_4;
        } else if (mcv.isInRange(MinecraftVersions.V7_2, MinecraftVersions.V7_10)) {
            version = MinecraftVersions.V7_10;
        } else if (mcv.isInRange(MinecraftVersions.V8, MinecraftVersions.V8_9)) {
            version = MinecraftVersions.V8_9;
        } else if (mcv.isInRange(MinecraftVersions.V9, MinecraftVersions.V9_4)) {
            version = MinecraftVersions.V9_4;
        } else if (mcv.isInRange(MinecraftVersions.V10, MinecraftVersions.V10_2)) {
            version = MinecraftVersions.V10_2;
        } else if (mcv.isInRange(MinecraftVersions.V11, MinecraftVersions.V11_2)) {
            version = MinecraftVersions.V11_2;
        } else if (mcv.isInRange(MinecraftVersions.V12, MinecraftVersions.V12_2)) {
            version = MinecraftVersions.V12_2;
        } else if (mcv.isInRange(MinecraftVersions.V13, MinecraftVersions.V13_2)) {
            version = MinecraftVersions.V13_2;
        } else if (mcv.isInRange(MinecraftVersions.V14, MinecraftVersions.V14_4)) {
            version = MinecraftVersions.V14_4;
        } else if (mcv.isInRange(MinecraftVersions.V15, MinecraftVersions.V15_2)) {
            version = MinecraftVersions.V15_2;
        } else if (mcv.isInRange(MinecraftVersions.V16, MinecraftVersions.V16_5)) {
            version = MinecraftVersions.V16_1;
        } else if (mcv.isInRange(MinecraftVersions.V17, MinecraftVersions.V17_1)) {
            version = MinecraftVersions.V17_1;
        } else if (mcv.isInRange(MinecraftVersions.V18, MinecraftVersions.V18_2)) {
            version = MinecraftVersions.V18_2;
        } else if (mcv.isInRange(MinecraftVersions.V19, MinecraftVersions.V19_4)) {
            version = MinecraftVersions.V19;
        } else if (mcv.isInRange(MinecraftVersions.V20, MinecraftVersions.V20_4)) {
            version = MinecraftVersions.V20_2;
        } else if (mcv.isInRange(MinecraftVersions.V20_5, MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V21_4;
        } else {
            version = MinecraftVersions.V21_4;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".forge";
    }

    @SuppressWarnings("IfStatementWithIdenticalBranches")
    public static String neoForge(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.is(MinecraftVersions.V20_2)) {
            version = MinecraftVersions.V20_2;
        } else if (mcv.isInRange(MinecraftVersions.V20_3, MinecraftVersions.V20_6)) {
            version = MinecraftVersions.V20_6;
        } else if (mcv.isInRange(MinecraftVersions.V21, MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V21_4;
        } else {
            version = MinecraftVersions.V21_4;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".neoforge";
    }

    public static String sponge(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersions.V8, MinecraftVersions.V10_2)) {
            version = MinecraftVersions.V10_2;
        } else if (mcv.isInRange(MinecraftVersions.V11, MinecraftVersions.V11_2)) {
            version = MinecraftVersions.V11_2;
        } else if (mcv.isInRange(MinecraftVersions.V12, MinecraftVersions.V12_2)) {
            version = MinecraftVersions.V12_2;
        } else if (mcv.isInRange(MinecraftVersions.V13, MinecraftVersions.V16_5)) {
            version = MinecraftVersions.V16_5;
        } else if (mcv.isInRange(MinecraftVersions.V17, MinecraftVersions.V18_2)) {
            version = MinecraftVersions.V18_2;
        } else if (mcv.isInRange(MinecraftVersions.V19, MinecraftVersions.V19_4)) {
            version = MinecraftVersions.V19_4;
        } else if (mcv.isInRange(MinecraftVersions.V20, MinecraftVersions.V20_6)) {
            version = MinecraftVersions.V20_6;
        } else if (mcv.isInRange(MinecraftVersions.V21, MinecraftVersions.V21_1)) {
            version = MinecraftVersions.V21_1;
        } else if (mcv.isInRange(MinecraftVersions.V21_2, MinecraftVersions.V21_3)) {
            version = MinecraftVersions.V21_3;
        } else if (mcv.is(MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V21_4;
        } else {
            version = MinecraftVersions.V21_4;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".sponge";
    }

    @SuppressWarnings("unused")
    public static String velocity(MinecraftVersion mcv) {
        return TL_PACKAGE + ".velocity.v3_3_0";
    }

    public static String vanilla(MinecraftVersion mcv) {
        MinecraftVersion version;
        if (mcv.isInRange(MinecraftVersions.B1_7, MinecraftVersions.B1_7_3)) {
            version = MinecraftVersions.B1_7_3;
        } else if (mcv.isInRange(MinecraftVersions.V2_1, MinecraftVersions.V2_5)) {
            version = MinecraftVersions.V2_5;
        } else if (mcv.isInRange(MinecraftVersions.V6_1, MinecraftVersions.V6_4)) {
            version = MinecraftVersions.V6_4;
        } else if (mcv.isInRange(MinecraftVersions.V7_2, MinecraftVersions.V7_10)) {
            version = MinecraftVersions.V7_10;
        } else if (mcv.isInRange(MinecraftVersions.V8, MinecraftVersions.V8_9)) {
            version = MinecraftVersions.V8_9;
        } else if (mcv.isInRange(MinecraftVersions.V9, MinecraftVersions.V9_4)) {
            version = MinecraftVersions.V9_4;
        } else if (mcv.isInRange(MinecraftVersions.V10, MinecraftVersions.V10_2)) {
            version = MinecraftVersions.V10_2;
        } else if (mcv.isInRange(MinecraftVersions.V11, MinecraftVersions.V11_2)) {
            version = MinecraftVersions.V11_2;
        } else if (mcv.isInRange(MinecraftVersions.V12, MinecraftVersions.V12_2)) {
            version = MinecraftVersions.V12_2;
        } else if (mcv.isInRange(MinecraftVersions.V13, MinecraftVersions.V14_4)) {
            version = MinecraftVersions.V14_4;
        } else if (mcv.isInRange(MinecraftVersions.V15, MinecraftVersions.V15_2)) {
            version = MinecraftVersions.V15_2;
        } else if (mcv.isInRange(MinecraftVersions.V16, MinecraftVersions.V16_1)) {
            version = MinecraftVersions.V16_1;
        } else if (mcv.isInRange(MinecraftVersions.V16_2, MinecraftVersions.V16_5)) {
            version = MinecraftVersions.V16_5;
        } else if (mcv.isInRange(MinecraftVersions.V17, MinecraftVersions.V17_1)) {
            version = MinecraftVersions.V17_1;
        } else if (mcv.isInRange(MinecraftVersions.V18, MinecraftVersions.V18_2)) {
            version = MinecraftVersions.V18_2;
        } else if (mcv.isInRange(MinecraftVersions.V19, MinecraftVersions.V19_4)) {
            version = MinecraftVersions.V19;
        } else if (mcv.isInRange(MinecraftVersions.V20, MinecraftVersions.V20_6)) {
            version = MinecraftVersions.V20;
        } else if (mcv.isInRange(MinecraftVersions.V21, MinecraftVersions.V21_4)) {
            version = MinecraftVersions.V21;
        } else {
            version = MinecraftVersions.V21;
        }
        return TL_PACKAGE + "." + version.getPathString() + ".vanilla";
    }

    public static <T> T newInstance(String clazz) {
        return newInstance(clazz, MetaAPI.instance().primaryPlatform());
    }

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "unchecked"})
    public static <T> T newInstance(String clazz, Platform platform, Object... args) {
        try {
            Class<?> objectClass = Class.forName(getClass(clazz, platform).get());
            return (T) objectClass.getConstructor().newInstance(args);
        } catch (ClassNotFoundException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException e) {
            logger.warn("Could not instantiate new instance of " + clazz, e);
        }
        return null;
    }
}
