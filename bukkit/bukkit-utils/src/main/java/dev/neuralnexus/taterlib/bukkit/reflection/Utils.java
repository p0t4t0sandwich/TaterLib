/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.reflection;

import dev.neuralnexus.taterapi.TaterAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;

public class Utils {
    private static final String CRAFTBUKKIT_PACKAGE =
            Bukkit.getServer().getClass().getPackage().getName();

    public static String cbClass(String clazz) {
        return CRAFTBUKKIT_PACKAGE + "." + clazz;
    }

    public static SimpleCommandMap getCommandMap() {
        // private CraftServer.commandMap
        try {
            Class<?> serverClass = Class.forName(cbClass("CraftServer"));
            Field commandMapField = serverClass.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (SimpleCommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            TaterAPI.logger().error("Could not reflect to get SimpleCommandMap", e);
            return null;
        }
    }

    public static void syncCommands() {
        // public CraftServer.syncCommands()
        try {
            Class<?> serverClass = Class.forName(cbClass("CraftServer"));
            serverClass.getDeclaredMethod("syncCommands").invoke(Bukkit.getServer());
        } catch (Exception e) {
            TaterAPI.logger().error("Could not reflect to get syncCommands", e);
        }
    }
}
