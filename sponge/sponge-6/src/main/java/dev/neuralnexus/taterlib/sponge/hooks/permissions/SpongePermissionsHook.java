package dev.neuralnexus.taterlib.sponge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.command.SpongeCommandSender;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

/** A hook for Sponge permissions */
public class SpongePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
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
            return ((SpongePlayer) commandSender).getPlayer().hasPermission(permission);
        } else {
            return (((SpongeCommandSender) commandSender).getSender().hasPermission(permission));
        }
    }
}
