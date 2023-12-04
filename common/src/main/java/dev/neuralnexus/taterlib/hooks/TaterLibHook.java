package dev.neuralnexus.taterlib.hooks;

/** A hook for TaterLib */
public class TaterLibHook implements Hook {
    private static TaterLibHook instance;

    /** Create a new hook */
    public TaterLibHook() {
        instance = this;
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static TaterLibHook get() {
        return instance;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "taterlib";
    }
}
