/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.util;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.util.ResourceLocation;

/** Vanilla implementation of {@link ResourceLocation}. */
public class VanillaResourceLocation implements ResourceLocation {
    private final net.minecraft.resources.ResourceLocation resourceLocation;

    /**
     * Creates a new resource location.
     *
     * @param resourceLocation The resource location.
     */
    public VanillaResourceLocation(net.minecraft.resources.ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    /**
     * Constructor.
     *
     * @param namespace The namespace.
     * @param path The path.
     */
    public VanillaResourceLocation(String namespace, String path) {
        this(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    /**
     * Constructor
     *
     * @param full The full resource location.
     */
    public VanillaResourceLocation(String full) {
        this(full.split(":")[0], full.split(":")[1]);
    }

    public net.minecraft.resources.ResourceLocation resourceLocation() {
        return resourceLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceLocation) {
            return full().equals(((ResourceLocation) obj).full());
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public String path() {
        return resourceLocation.getPath();
    }

    /** {@inheritDoc} */
    @Override
    public String namespace() {
        return resourceLocation.getNamespace();
    }

    public static class Builder implements ResourceLocation.Builder {
        private String namespace;
        private String path;

        @Override
        public ResourceLocation.Builder full(String full) {
            this.namespace = full.split(":")[0];
            this.path = full.split(":")[1];
            return this;
        }

        @Override
        public ResourceLocation.Builder unsafeFull(String full) {
            throw new VersionFeatureNotSupportedException();
        }

        /** {@inheritDoc} */
        @Override
        public Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder path(String path) {
            this.path = path;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public VanillaResourceLocation build() {
            return new VanillaResourceLocation(namespace, path);
        }
    }
}
