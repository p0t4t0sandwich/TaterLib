package dev.neuralnexus.taterlib.v1_20_4.bukkit.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.command.BukkitCommandSender;

import org.bukkit.Bukkit;

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
            org.bukkit.entity.Player player = Bukkit.getPlayer(commandSender.getUniqueId());
            if (player != null) {
                return player.hasPermission(permission);
            }
            return false;
        }
        return ((BukkitCommandSender) commandSender).getSender().hasPermission(permission);
    }
}
