package dev.neuralnexus.taterlib.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.legacyfabric.fabric.api.permission.v1.PermissionsApiHolder;
import net.minecraft.entity.player.ServerPlayerEntity;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "fabricpermissions";
    }

    /**
     * Get if a sender has a permission
     *
     * @param commandSender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @SuppressWarnings({"deprecation", "UnstableApiUsage"})
    @Override
    public boolean hasPermission(CommandSender commandSender, String permission) {
        if (commandSender instanceof Player) {
            return PermissionsApiHolder.getPlayerPermissionsApi()
                    .hasPermission(
                            (ServerPlayerEntity) ((FabricPlayer) commandSender).getPlayer(), permission);
        } else {
            return ((FabricCommandSender) commandSender).getSender().hasPermission(permission);
        }
    }
}
