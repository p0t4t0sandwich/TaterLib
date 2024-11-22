package dev.neuralnexus.modapi.metadata;

public final class Platforms {
    private static PlatformNew[] platforms;

    /**
     * Returns all platforms that are available.
     * @return An array of all available platforms
     */
    public static PlatformNew[] platforms() {
        if (platforms == null) {
            platforms = new PlatformNew[] {};
        }
        return platforms;
    }

    /**
     * Detects all platforms that are available.
     * @return An array of all detected platforms
     */
    static PlatformNew[] detectPlaforms() {
        // TODO: Implement
        return null;
    }
}
