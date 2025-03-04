/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.api;

import org.jetbrains.annotations.ApiStatus;

/** API Provider */
public class TestModAPIProvider {
    private static TestModAPI instance = null;

    /**
     * Get the instance of the API
     *
     * @return The instance of the API
     */
    public static TestModAPI get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param instance: The instance of TaterUtils
     */
    @ApiStatus.Internal
    public static void register(TestModAPI instance) {
        if (TestModAPIProvider.instance != null) {
            throw new IllegalStateException("ExampleAPI has already been registered!");
        }
        TestModAPIProvider.instance = instance;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void unregister() {
        instance = null;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the BeeNameGenerator
     * plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE =
                "The API hasn't loaded yet, or you don't have the TaterUtils plugin installed.";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
}
