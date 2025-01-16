package dev.neuralnexus.modapi.crossperms.api.mc;

/**
 * A marker interface for wrapped objects.
 */
public interface Wrapped {
    /**
     * Unwrap the object.
     *
     * @return The unwrapped object
     */
    Object unwrap();
}
