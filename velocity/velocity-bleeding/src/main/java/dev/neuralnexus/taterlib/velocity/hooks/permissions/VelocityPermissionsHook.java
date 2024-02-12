package dev.neuralnexus.taterlib.velocity.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.velocity.command.VelocityCommandSender;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/** A hook for Velocity permissions */
public class VelocityPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "spongepermissions";
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
            return ((VelocityPlayer) commandSender).player().hasPermission(permission);
        } else {
            return ((VelocityCommandSender) commandSender).sender().hasPermission(permission);
        }
    }
}
