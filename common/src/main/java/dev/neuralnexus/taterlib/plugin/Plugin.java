package dev.neuralnexus.taterlib.plugin;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

/** General plugin interface. */
public interface Plugin {
    /** Start the plugin. */
    void pluginStart(Object plugin, AbstractLogger logger);

    /** Stop the plugin. */
    void pluginStop();
}
