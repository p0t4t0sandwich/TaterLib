/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.resources;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.client.resource.Identifier;

/** Vanilla implementation of {@link ResourceKey}. */
public class VanillaResourceKey {
    public static class Factory implements ResourceKey.Factory {

        @Override
        @SuppressWarnings("DataFlowIssue")
        public ResourceKey of(String namespace, String value) {
            return (ResourceKey) new Identifier(namespace, value);
        }

        @SuppressWarnings("DataFlowIssue")
        @Override
        public ResourceKey of(String full) {
            return (ResourceKey) new Identifier(full);
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
        @SuppressWarnings("DataFlowIssue")
        public ResourceKey build() {
            return (ResourceKey) new Identifier(this.namespace, this.value);
        }
    }
}
