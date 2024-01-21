package dev.neuralnexus.taterlib.sponge.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.command.SpongeSender;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import org.spongepowered.api.entity.living.player.server.ServerPlayer;

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
     * @param sender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(Sender sender, String permission) {
        if (sender instanceof Player) {
            return ((ServerPlayer) ((SpongePlayer) sender).getPlayer()).hasPermission(permission);
        } else {
            return (((SpongeSender) sender).getSender().hasPermission(permission));
        }
    }
}
