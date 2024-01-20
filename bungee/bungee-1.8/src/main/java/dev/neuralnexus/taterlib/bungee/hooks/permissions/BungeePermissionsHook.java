package dev.neuralnexus.taterlib.bungee.hooks.permissions;

import dev.neuralnexus.taterlib.bungee.command.BungeeSender;
import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

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
     * @param sender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(Sender sender, String permission) {
        if (sender instanceof Player) {
            return ((BungeePlayer) sender).getPlayer().hasPermission(permission);
        } else {
            return ((BungeeSender) sender).getSender().hasPermission(permission);
        }
    }
}
