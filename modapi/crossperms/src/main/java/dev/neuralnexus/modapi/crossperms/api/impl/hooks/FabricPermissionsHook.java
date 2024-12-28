package dev.neuralnexus.modapi.crossperms.api.impl.hooks;

import dev.neuralnexus.modapi.crossperms.api.PermissionsHook;
import me.lucko.fabric.api.permissions.v0.Permissions;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "fabricpermissions";
    }

    @Override
    public boolean hasPermission(Object source, String permission) {
        if (source instanceof Player) {
            return Permissions.check((Entity) permissible, permission, 4);
        } else if (source instanceof CommandSender) {
            return Permissions.check((CommandSourceStack) permissible, permission, 4);
        }
        return false;
    }
}
