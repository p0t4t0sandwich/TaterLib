/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.resource;

import dev.neuralnexus.taterapi.TaterAPIProvider;

/** An abstraction for resource locations. */
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
        return TaterAPIProvider.getFactory(Factory.class).of(namespace, value);
    }

    static ResourceKey of(String full) {
        return TaterAPIProvider.getFactory(Factory.class).of(full);
    }

    static ResourceKey unsafeOf(String full) {
        return TaterAPIProvider.getFactory(Factory.class).unsafeOf(full);
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
        return TaterAPIProvider.getBuilder(Builder.class);
    }

    /** A factory for resource locations. */
    interface Factory {
        ResourceKey of(String namespace, String value);

        ResourceKey of(String full);

        ResourceKey unsafeOf(String full);
    }

    /** A builder for resource locations. */
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
