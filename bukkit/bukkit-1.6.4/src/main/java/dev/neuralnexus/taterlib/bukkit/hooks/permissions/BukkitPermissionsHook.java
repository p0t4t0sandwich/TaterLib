package dev.neuralnexus.taterlib.bukkit.hooks.permissions;

import dev.neuralnexus.taterlib.bukkit.command.BukkitCommandSender;
import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

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
     * @param commandSender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(CommandSender commandSender, String permission) {
        if (commandSender instanceof Player) {
            return ((BukkitPlayer) commandSender).getPlayer().hasPermission(permission);
        } else {
            return ((BukkitCommandSender) commandSender).getSender().hasPermission(permission);
        }
    }
}
