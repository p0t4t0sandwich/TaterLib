package dev.neuralnexus.taterlib.v_1_13_2.bukkit.hooks.permissions;

import dev.neuralnexus.taterlib.v_1_13_2.bukkit.command.BukkitCommandSender;
import dev.neuralnexus.taterlib.v_1_13_2.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

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
            return ((BukkitPlayer) permissible).player().hasPermission(permission);
        } else if (permissible instanceof CommandSender) {
            return ((BukkitCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
