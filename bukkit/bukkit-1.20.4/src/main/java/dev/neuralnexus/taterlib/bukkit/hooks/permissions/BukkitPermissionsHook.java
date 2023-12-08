package dev.neuralnexus.taterlib.bukkit.hooks.permissions;

import dev.neuralnexus.taterlib.bukkit.command.BukkitSender;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;

/** A hook for Bukkit permissions */
public class BukkitPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "bukkitpermissions";
    }

    /**
     * Get if a sender has a permission
     *
     * @param sender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(Sender sender, String permission) {
        return ((BukkitSender) sender).getSender().hasPermission(permission);
    }
}
