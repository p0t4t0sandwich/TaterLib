package dev.neuralnexus.taterapi.hooks;

import java.util.UUID;

public interface PrefixManager {
    /**
     * Get the prefix for a user
     * @param id The UUID of the user
     * @return The prefix for the user
     */
    String prefix(UUID id);

    /**
     * Set the prefix for a user
     * @param id The UUID of the user
     * @param prefix The prefix to set
     */
    default void setPrefix(UUID id, String prefix) {
        this.setPrefix(id, prefix, 0);
    }

    /**
     * Set the prefix for a user
     * @param id The UUID of the user
     * @param prefix The prefix to set
     * @param priority The priority of the prefix
     */
    void setPrefix(UUID id, String prefix, int priority);

    /**
     * Get the suffix for a user
     * @param id The UUID of the user
     * @return The suffix for the user
     */
    String suffix(UUID id);

    /**
     * Set the suffix for a user
     * @param id The UUID of the user
     * @param suffix The suffix to set
     */
    default void setSuffix(UUID id, String suffix) {
        this.setSuffix(id, suffix, 0);
    }

    /**
     * Set the suffix for a user
     * @param id The UUID of the user
     * @param suffix The suffix to set
     * @param priority The priority of the suffix
     */
    void setSuffix(UUID id, String suffix, int priority);
}
