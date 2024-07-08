package dev.neuralnexus.taterapi.util;

import dev.neuralnexus.taterapi.Builders;

/**
 * An abstraction for resource locations.
 */
public interface ResourceLocation {
    /**
     * Gets the path of the resource.
     *
     * @return The path of the resource
     */
    String path();

    /**
     * Gets the namespace of the resource.
     *
     * @return The namespace of the resource
     */
    String namespace();

    /**
     * Gets the full resource location.
     *
     * @return The full resource location
     */
    default String full() {
        return namespace() + ":" + path();
    }

    /**
     * Creates a new builder for resource locations.
     *
     * @return The builder
     */
    static Builder builder() {
        return Builders.resourceLocationBuilder.get();
    }

    /**
     * A builder for resource locations.
     */
    interface Builder {
        /**
         * Set the full resource location.
         *
         * @param full The full resource location
         */
        Builder full(String full);

        /**
         * Sets the full resource location unsafely.
         *
         * @param full The full resource location
         */
        Builder unsafeFull(String full);

        /**
         * Sets the path of the resource.
         *
         * @param path The path of the resource
         * @return This builder
         */
        Builder path(String path);

        /**
         * Sets the namespace of the resource.
         *
         * @param namespace The namespace of the resource
         * @return This builder
         */
        Builder namespace(String namespace);

        /**
         * Builds the resource location.
         *
         * @return The resource location
         */
        ResourceLocation build();
    }
}
