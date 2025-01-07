/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/** Bukkit permissions provider */
public class BukkitPermissionsProvider implements PermissionsProvider {
    @Override
    public String name() {
        return "bukkitpermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        // TODO: Reflect to query the player object
        // It's gonna suck to get all those obsf mappings
        if (subject instanceof CommandSender commandSender) {
            return commandSender.isOp();
        }
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        boolean result = false;
        if (subject instanceof CommandSender commandSender) {
            result = commandSender.hasPermission(permission);
        }
        return result | this.hasPermission(subject, 4);
    }
}
