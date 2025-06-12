/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.perms;

import java.lang.reflect.ParameterizedType;

@FunctionalInterface
public interface HasPermission<P, S> {
    /**
     * Get the type of permission this provider uses
     *
     * @return The type of permission
     */
    @SuppressWarnings("unchecked")
    default Class<P> type() {
        return (Class<P>)
                ((ParameterizedType) getClass().getGenericInterfaces()[0])
                        .getActualTypeArguments()[0];
    }

    /**
     * Check if a source has a permission, which can be anything depending on the provider, but
     * usually a string or int
     *
     * @param subject The source to check
     * @param permission The permission to check
     * @return If the source has the permission
     */
    boolean hasPermission(S subject, P permission);
}
