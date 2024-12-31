package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/** BungeeCord permissions provider */
public class BungeeCordPermissionsProvider implements PermissionsProvider {
    @Override
    public String name() {
        return "bungeecordpermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        if (subject instanceof CommandSender bungeeSender) {
            return bungeeSender.hasPermission(permission);
        }
        return false;
    }
}
