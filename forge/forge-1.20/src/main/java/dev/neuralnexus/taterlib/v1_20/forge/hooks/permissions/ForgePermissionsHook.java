package dev.neuralnexus.taterlib.v1_20.forge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.nodes.PermissionTypes;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "forgepermissions";
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
        if (commandSender.hasPermission(4)) {
            return true;
        }

        if (commandSender instanceof Player) {
            return PermissionAPI.getRegisteredNodes().stream()
                    .filter(node -> node.getType() == PermissionTypes.BOOLEAN)
                    .filter(node -> node.getNodeName().equals(permission))
                    .anyMatch(
                            node ->
                                    (boolean)
                                            node.getDefaultResolver()
                                                    .resolve(
                                                            (ServerPlayer)
                                                                    ((VanillaPlayer) commandSender)
                                                                            .player(),
                                                            commandSender.uuid()));
        }
        return false;
    }
}
