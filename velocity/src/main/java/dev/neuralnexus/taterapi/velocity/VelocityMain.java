package dev.neuralnexus.taterapi.velocity;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import dev.neuralnexus.taterapi.velocity.commands.VelocityTaterAPICommand;
import dev.neuralnexus.taterapi.velocity.listeners.VelocityPlayerLoginListener;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "taterapi",
        name = "TaterAPI",
        version = "1.0.0",
        authors = "p0t4t0sandwich",
        description = "A cross API code library for various generalizations used in the Tater* plugins",
        url = "https://github.com/p0t4t0sandwich/TaterAPI",
        dependencies = {}
)
public class VelocityMain {
    public static TaterAPI taterApi;

    @Inject
    private ProxyServer server;

    @Inject
    private Logger logger;

    // Get server type
    public String getServerType() {
        return "Velocity";
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("TaterAPI is running on " + getServerType() + ".");

        // Start TaterAPI
        taterApi = new TaterAPI("plugins", logger);
        TaterAPI.start();

        // Register event listener
        server.getEventManager().register(this, new VelocityPlayerLoginListener());

        // Register commands
        server.getCommandManager().register(TaterAPICommand.commandName, new VelocityTaterAPICommand());

        // Plugin enable message
        logger.info("TaterAPI has been enabled!");
    }
}
