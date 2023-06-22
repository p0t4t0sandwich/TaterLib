package dev.neuralnexus.taterapi.common.api;

import dev.neuralnexus.taterapi.common.TaterAPI;

public class TaterAPIProvider {
    private static TaterAPI instance = null;

    /**
     * Get the instance of AMPServerManager
     * @return The instance of AMPServerManager
     */
    public static TaterAPI get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param instance: The instance of LPPronouns
     */
    public static void register(TaterAPI instance) {
        TaterAPIProvider.instance = instance;
    }

    public static void unregister() {
        TaterAPIProvider.instance = null;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the LPPronouns plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = "The API hasn't loaded yet, or you don't have the AMPServerManager plugin installed.";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
}
