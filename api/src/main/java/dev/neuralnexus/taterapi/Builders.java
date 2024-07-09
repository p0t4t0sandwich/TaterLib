package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.util.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/** A class that stores registered builders. */
@ApiStatus.Internal
public class Builders {
    public static Supplier<Location.Builder> locationBuilder = () -> null;
    public static Supplier<ResourceKey.Builder> resourceKeyBuilder = () -> null;
}
