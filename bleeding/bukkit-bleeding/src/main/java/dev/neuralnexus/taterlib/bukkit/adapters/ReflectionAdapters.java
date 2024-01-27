package dev.neuralnexus.taterlib.bukkit.adapters;

import net.minecraft.server.MinecraftServer;

import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;

/** Utility class to adapt Bukkit to Vanilla NMS classes */
public class ReflectionAdapters {
    private static final String CRAFTBUKKIT_PACKAGE =
            Bukkit.getServer().getClass().getPackage().getName();

    /**
     * Returns the CraftBukkit class name for the given class.
     *
     * @param clazz The class name.
     * @return The CraftBukkit class name.
     */
    public static String cbClass(String clazz) {
        return CRAFTBUKKIT_PACKAGE + "." + clazz;
    }

    /**
     * Returns the CraftBukkit class name for the given class.
     *
     * @param className The class name.
     * @param object The object to cast.
     * @return The casted object.
     */
    public static Object reflectAndCast(String className, Object object) {
        try {
            return Class.forName(className).cast(object);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Minecraft server.
     *
     * @return The Minecraft server.
     */
    public static MinecraftServer getServer() {
        // ((CraftServer) Bukkit.getServer()).getServer();
        Object craftServer = reflectAndCast(cbClass("CraftServer"), Bukkit.getServer());
        try {
            return (MinecraftServer)
                    craftServer.getClass().getDeclaredMethod("getServer").invoke(craftServer);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
