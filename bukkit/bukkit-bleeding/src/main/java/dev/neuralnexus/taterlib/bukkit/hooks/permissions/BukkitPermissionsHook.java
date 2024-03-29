package dev.neuralnexus.taterlib.bukkit.hooks.permissions;

import dev.neuralnexus.taterlib.bukkit.command.BukkitCommandSender;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import org.bukkit.Bukkit;

/** A hook for Bukkit permissions */
public class BukkitPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "bukkitpermissions";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            org.bukkit.entity.Player player = Bukkit.getPlayer(permissible.uuid());
            if (player != null) {
                return player.hasPermission(permission);
            }
            return false;
        } else if (permissible instanceof CommandSender) {
            return ((BukkitCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
