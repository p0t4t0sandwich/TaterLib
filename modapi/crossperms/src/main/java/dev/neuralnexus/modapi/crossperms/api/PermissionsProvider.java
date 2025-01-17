/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api;

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
