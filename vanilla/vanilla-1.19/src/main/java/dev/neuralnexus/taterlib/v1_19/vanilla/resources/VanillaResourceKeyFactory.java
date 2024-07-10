/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.vanilla.resources;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.resources.ResourceLocation;

/** Vanilla implementation of {@link ResourceKey.Factory}. */
public class VanillaResourceKeyFactory implements ResourceKey.Factory {

    @Override
    public ResourceKey of(String namespace, String value) {
        return (ResourceKey) (Object) new ResourceLocation(namespace, value);
    }

    @Override
    public ResourceKey of(String full) {
        return (ResourceKey) (Object) ResourceLocation.tryParse(full);
    }

    @Override
    public ResourceKey unsafeOf(String full) {
        throw new VersionFeatureNotSupportedException();
    }
}
