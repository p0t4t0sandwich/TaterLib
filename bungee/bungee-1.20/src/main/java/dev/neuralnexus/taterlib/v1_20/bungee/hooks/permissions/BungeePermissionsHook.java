package dev.neuralnexus.taterlib.v1_20.bungee.hooks.permissions;

import dev.neuralnexus.taterlib.v1_20.bungee.command.BungeeCommandSender;
import dev.neuralnexus.taterlib.v1_20.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

/** A hook for Bungee permissions */
public class BungeePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "bungeepermissions";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof ProxyPlayer) {
            return ((BungeePlayer) permissible).player().hasPermission(permission);
        } else if (permissible instanceof CommandSender) {
            return ((BungeeCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
