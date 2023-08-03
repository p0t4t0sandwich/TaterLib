package dev.neuralnexus.taterlib.common.api;

import dev.neuralnexus.taterlib.common.TaterLib;

/**
 * TaterLib API Provider
 */
public class TaterLibAPIProvider {
    private static TaterLib instance = null;

    /**
     * Get the instance of TaterLib
     * @return The instance of TaterLib
     */
    public static TaterLib get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param instance: The instance of TaterLib
     */
    public static void register(TaterLib instance) {
        TaterLibAPIProvider.instance = instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     */
    public static void unregister() {
        TaterLibAPIProvider.instance = null;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the TaterLib plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = "The API hasn't loaded yet, or you don't have the TaterLib plugin installed.";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
}
