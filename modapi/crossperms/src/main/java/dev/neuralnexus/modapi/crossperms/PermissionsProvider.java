/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** A generic provider for permissions plugins */
public interface PermissionsProvider {
    /**
     * Get the providers for the provider
     *
     * @return The providers for the provider
     */
    @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders();
}
