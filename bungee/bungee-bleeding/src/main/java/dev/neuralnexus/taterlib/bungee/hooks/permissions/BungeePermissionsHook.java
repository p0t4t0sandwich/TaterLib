package dev.neuralnexus.taterlib.bungee.hooks.permissions;

import dev.neuralnexus.taterlib.bungee.command.BungeeCommandSender;
import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

/** A hook for Bungee permissions */
public class BungeePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "bungeepermissions";
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
        if (commandSender instanceof ProxyPlayer) {
            return ((BungeePlayer) commandSender).getPlayer().hasPermission(permission);
        } else {
            return ((BungeeCommandSender) commandSender).getSender().hasPermission(permission);
        }
    }
}
