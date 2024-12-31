package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.service.permission.Subject;

import java.util.Objects;

/** Sponge permissions provider */
public class SpongePermissionsProvider implements PermissionsProvider {
    @Override
    public String name() {
        return "spongepermissions";
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
        if (subject instanceof Subject spongeSubject) {
            return spongeSubject.hasPermission(permission);
        }
        return result | this.hasPermission(subject, 4);
    }
}
