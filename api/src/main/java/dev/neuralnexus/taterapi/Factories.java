package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.resource.ResourceKeyImpl;

import java.util.function.Supplier;

/** A class that stores registered factories. */
public class Factories {
    public static Supplier<ResourceKey.Factory> resourceKeyFactory = ResourceKeyImpl.Factory::new;
}
