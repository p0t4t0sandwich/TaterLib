package dev.neuralnexus.taterlib.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
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
    public String name() {
        return "fabricpermissions";
    }

    /** {@inheritDoc} */
    @SuppressWarnings({"deprecation", "UnstableApiUsage"})
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return PermissionsApiHolder.getPlayerPermissionsApi()
                    .hasPermission(
                            (ServerPlayerEntity) ((FabricPlayer) permissible).player(), permission);
        } else if (permissible instanceof CommandSender) {
            return ((FabricCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
