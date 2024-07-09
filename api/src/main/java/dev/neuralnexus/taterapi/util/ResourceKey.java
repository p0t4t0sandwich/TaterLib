package dev.neuralnexus.taterapi.util;

import dev.neuralnexus.taterapi.Builders;
import dev.neuralnexus.taterapi.Factories;

/**
 * An abstraction for resource locations.
 */
public interface ResourceKey {
    /**
     * Gets the path of the resource.
     *
     * @return The path of the resource
     */
    String value();

    /**
     * Gets the namespace of the resource.
     *
     * @return The namespace of the resource
     */
    String namespace();

    static ResourceKey of(String namespace, String value) {
        return Factories.resourceKeyFactory.get().of(namespace, value);
    }

    static ResourceKey of(String full) {
        return Factories.resourceKeyFactory.get().of(full);
    }

    static ResourceKey unsafeOf(String full) {
        return Factories.resourceKeyFactory.get().unsafeOf(full);
    }

    /**
     * Gets the full resource location.
     *
     * @return The full resource location
     */
    default String asString() {
        return namespace() + ":" + value();
    }

    /**
     * Creates a new builder for resource locations.
     *
     * @return The builder
     */
    static Builder builder() {
        return Builders.resourceKeyBuilder.get();
    }

    /**
     * A factory for resource locations.
     */
    interface Factory {
        ResourceKey of(String namespace, String value);

        ResourceKey of(String full);

        ResourceKey unsafeOf(String full);
    }

    /**
     * A builder for resource locations.
     */
    interface Builder {
        /**
         * Sets the namespace of the resource.
         *
         * @param namespace The namespace of the resource
         * @return This builder
         */
        Builder namespace(String namespace);

        /**
         * Sets the value of the resource.
         *
         * @param value The value of the resource
         * @return This builder
         */
        Builder value(String value);

        /**
         * Builds the resource location.
         *
         * @return The resource location
         */
        ResourceKey build();
    }
}
