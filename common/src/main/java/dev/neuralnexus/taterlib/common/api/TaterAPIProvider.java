package dev.neuralnexus.taterlib.common.api;

/**
 * TaterAPI Provider
 */
public class TaterAPIProvider {
    private static TaterAPI instance = null;

    /**
     * Get the instance of TaterAPI
     * @return The instance of TaterAPI
     */
    public static TaterAPI get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param configFolder The config folder
     * @param minecraftVersion The Minecraft version
     */
    public static void register(String configFolder, String minecraftVersion) {
        if (TaterAPIProvider.instance != null) {
            throw new IllegalStateException("TaterAPI has already been registered!");
        }
        TaterAPIProvider.instance = new TaterAPI(configFolder, minecraftVersion);
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     */
    public static void unregister() {
        TaterAPIProvider.instance = null;
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
