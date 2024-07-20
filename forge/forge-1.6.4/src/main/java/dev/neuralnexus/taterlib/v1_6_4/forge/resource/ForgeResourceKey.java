/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.resource;

import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.util.ResourceLocation;

/** An abstraction for resource locations. */
public class ForgeResourceKey implements ResourceKey {
    private final ResourceLocation resourceLocation;

    /**
     * Constructor.
     *
     * @param resourceLocation The resource location.
     */
    public ForgeResourceKey(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    /**
     * Constructor.
     *
     * @param namespace The namespace.
     * @param value The value.
     */
    public ForgeResourceKey(String namespace, String value) {
        this.resourceLocation = new ResourceLocation(namespace, value);
    }

    /**
     * Constructor
     *
     * @param full The full resource location.
     */
    public ForgeResourceKey(String full) {
        this.resourceLocation = new ResourceLocation(full);
    }

    /**
     * Get the resource location.
     *
     * @return The resource location.
     */
    public ResourceLocation resourceLocation() {
        return resourceLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceKey) {
            return asString().equals(((ResourceKey) obj).asString());
        }
        return false;
    }

    @Override
    public String value() {
        return resourceLocation.getResourcePath();
    }

    @Override
    public String namespace() {
        return resourceLocation.getResourceDomain();
    }

    public static class Factory implements ResourceKey.Factory {
        @Override
        public ResourceKey of(String namespace, String value) {
            return new ForgeResourceKey(namespace, value);
        }

        @Override
        public ResourceKey of(String full) {
            return new ForgeResourceKey(full);
        }

        @Override
        public ResourceKey unsafeOf(String full) {
            return new ForgeResourceKey(full);
        }
    }

    public static class Builder implements ResourceKey.Builder {
        private String namespace;
        private String value;

        @Override
        public ResourceKey.Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        @Override
        public ResourceKey.Builder value(String value) {
            this.value = value;
            return this;
        }

        @Override
        public ResourceKey build() {
            return new ForgeResourceKey(namespace, value);
        }
    }
}
