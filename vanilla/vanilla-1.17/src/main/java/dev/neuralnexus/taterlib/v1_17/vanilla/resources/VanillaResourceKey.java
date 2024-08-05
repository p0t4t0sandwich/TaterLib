/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17.vanilla.resources;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.resources.ResourceLocation;

/** Vanilla implementation of {@link ResourceKey}. */
@SuppressWarnings("DataFlowIssue")
public class VanillaResourceKey {
    public static class Factory implements ResourceKey.Factory {

        @Override
        public ResourceKey of(String namespace, String value) {
            return (ResourceKey) new ResourceLocation(namespace, value);
        }

        @Override
        public ResourceKey of(String full) {
            return (ResourceKey) ResourceLocation.tryParse(full);
        }

        @Override
        public ResourceKey unsafeOf(String full) {
            throw new VersionFeatureNotSupportedException();
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
            return (ResourceKey) new ResourceLocation(this.namespace, this.value);
        }
    }
}
