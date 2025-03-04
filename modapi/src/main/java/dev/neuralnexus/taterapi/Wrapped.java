/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi;

/** Indicates if a platform object is wrapped */
public interface Wrapped<T> {
    /**
     * Unwrap the object.
     *
     * @return The unwrapped object
     */
    T unwrap();
}
