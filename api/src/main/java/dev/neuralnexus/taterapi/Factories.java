package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.util.ResourceKey;

import java.util.function.Supplier;

/** A class that stores registered factories. */
public class Factories {
    public static Supplier<ResourceKey.Factory> resourceKeyFactory = () -> null;
}
