package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.logger.AbstractLogger;

/**
 * General plugin interface.
 */
public interface Plugin {
    /**
     * Start the plugin.
     */
    void pluginStart(Object plugin, AbstractLogger logger);

    /**
     * Stop the plugin.
     */
    void pluginStop();
}
