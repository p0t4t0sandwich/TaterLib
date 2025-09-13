/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.impl.providers;

import dev.neuralnexus.taterapi.perms.HasPermission;
import dev.neuralnexus.taterapi.perms.PermissionsProvider;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.service.permission.Subject;

import java.util.List;
import java.util.Map;

/** Sponge permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class SpongePermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Subject.class,
                List.of(
                        new HasPermission<String, Subject>() {
                            @Override
                            public boolean hasPermission(Subject subject, String permission) {
                                return subject.hasPermission(permission);
                            }
                        }));
    }
}
