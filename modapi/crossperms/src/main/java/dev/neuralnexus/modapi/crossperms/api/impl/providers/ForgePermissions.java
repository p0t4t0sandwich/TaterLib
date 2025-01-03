package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import net.minecraftforge.server.permission.PermissionAPI;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/** Forge permissions provider */
public class ForgePermissions implements PermissionsProvider {
    @Override
    public String name() {
        return "forgepermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        // TODO: Reflect to query the player object
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        boolean result = false;
        GameProfile profile = null;
        // TODO: Split this into a separate method
        if (subject instanceof GameProfile gameProfile) {
            profile = gameProfile;
        }
        // TODO: Reflectively check for instance of player, then reflect to get profile
        if (profile != null) {
            result = PermissionAPI.getPermissionHandler().hasPermission(profile, permission, null);
        }

        return result | this.hasPermission(subject, 4);
    }
}
