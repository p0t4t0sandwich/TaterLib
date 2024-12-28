package dev.neuralnexus.modapi.crossperms.api.impl.hooks;

import dev.neuralnexus.modapi.crossperms.api.PermissionsHook;
import org.bukkit.command.CommandSender;

/** A hook for Bukkit permissions */
public class BukkitPermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "bukkitpermissions";
    }

    @Override
    public boolean hasPermission(Object source, String permission) {
        if (source instanceof CommandSender commandSender) {
            return commandSender.hasPermission(permission);
        }
        return false;
    }
}
