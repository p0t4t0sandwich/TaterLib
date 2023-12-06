package dev.neuralnexus.taterlib.neoforge.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;

/** A hook for NeoForge permissions */
public class NeoForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "neoforgepermissions";
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
        if (sender.hasPermission(4)) {
            return true;
        }

        if (sender instanceof Player) {
            return PermissionAPI.getRegisteredNodes().stream()
                    .filter(node -> node.getType() == PermissionTypes.BOOLEAN)
                    .filter(node -> node.getNodeName().equals(permission))
                    .anyMatch(
                            node ->
                                    (boolean)
                                            node.getDefaultResolver()
                                                    .resolve(
                                                            (ServerPlayer)
                                                                    ((NeoForgePlayer) sender)
                                                                            .getPlayer(),
                                                            sender.getUniqueId()));
        }
        return false;
    }
}
