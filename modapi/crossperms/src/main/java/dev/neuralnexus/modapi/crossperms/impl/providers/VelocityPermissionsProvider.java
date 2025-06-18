/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.impl.providers;

import com.velocitypowered.api.permission.PermissionSubject;

import dev.neuralnexus.modapi.crossperms.HasPermission;
import dev.neuralnexus.modapi.crossperms.PermissionsProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** Velocity permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class VelocityPermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                PermissionSubject.class,
                List.of(
                        new HasPermission<String, PermissionSubject>() {
                            @Override
                            public boolean hasPermission(
                                    PermissionSubject subject, String permission) {
                                return subject.hasPermission(permission);
                            }
                        }));
    }
}
