/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.hooks;

import dev.neuralnexus.modapi.crossperms.api.PermissionsHook;

import org.bukkit.command.CommandSender;

import java.util.Objects;

/** A hook for Bukkit permissions */
public class BukkitPermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "bukkitpermissions";
    }

    @Override
    public boolean hasPermission(Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        if (subject instanceof CommandSender commandSender) {
            return commandSender.isOp();
        }
        return false;
    }

    @Override
    public boolean hasPermission(Object subject, String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        if (subject instanceof CommandSender commandSender) {
            return commandSender.hasPermission(permission);
        }
        return false;
    }
}
