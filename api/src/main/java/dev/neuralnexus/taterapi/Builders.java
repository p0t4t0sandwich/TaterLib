/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.resource.impl.ResourceKeyImpl;
import dev.neuralnexus.taterapi.world.Location;

import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/** A class that stores registered builders. */
@ApiStatus.Internal
public class Builders {
    public static Supplier<Location.Builder> locationBuilder = () -> null;
    public static Supplier<ResourceKey.Builder> resourceKeyBuilder = ResourceKeyImpl.Builder::new;
}
