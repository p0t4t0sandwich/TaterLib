package dev.neuralnexus.taterlib.velocity.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.velocity.command.VelocitySender;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/** A hook for Velocity permissions */
public class VelocityPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "spongepermissions";
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
            return ((VelocityPlayer) sender).getPlayer().hasPermission(permission);
        } else {
            return ((VelocitySender) sender).getSender().hasPermission(permission);
        }
    }
}
