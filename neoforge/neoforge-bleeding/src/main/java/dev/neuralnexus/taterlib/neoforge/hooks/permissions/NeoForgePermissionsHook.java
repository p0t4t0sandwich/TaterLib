package dev.neuralnexus.taterlib.neoforge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;

/** A hook for NeoForge permissions */
public class NeoForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "neoforgepermissions";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible.hasPermission(4)) {
            return true;
        }

        ServerPlayer player;
        if (permissible instanceof Player) {
            player = (ServerPlayer) ((VanillaPlayer) permissible).player();
        } else if (permissible instanceof CommandSender) {
            player = (ServerPlayer) ((VanillaCommandSender) permissible).sender().getEntity();
        } else {
            player = null;
        }

        if (player != null) {
            return PermissionAPI.getRegisteredNodes().stream()
                    .filter(node -> node.getType() == PermissionTypes.BOOLEAN)
                    .filter(node -> node.getNodeName().equals(permission))
                    .anyMatch(
                            node ->
                                    (boolean)
                                            node.getDefaultResolver()
                                                    .resolve(player, permissible.uuid()));
        }
        return false;
    }
}
