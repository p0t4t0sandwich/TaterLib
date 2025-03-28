/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod;

import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterapi.loader.plugin.Plugin;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.testmod.api.TestModAPI;
import dev.neuralnexus.taterlib.testmod.api.TestModAPIProvider;
import dev.neuralnexus.taterlib.testmod.commands.PingPongCommand;

/** Main class for the plugin. */
public class TestMod implements Plugin {
    public static final String PROJECT_NAME = "TestMod";
    public static final String PROJECT_ID = "testmod";
    public static final String PROJECT_VERSION = "0.1.0-R0.1-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
    public static final String PROJECT_DESCRIPTION = "A cross-API plugin that uses TaterLib!";
    public static final String PROJECT_URL =
            "https://github.com/p0t4t0sandwich/TaterLib/tree/main/testmod";

    private static final TestMod instance = new TestMod();
    private static final Logger logger = Logger.create(TestMod.PROJECT_ID);
    private static boolean RELOADED = false;

    private TestMod() {}

    /**
     * Getter for the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static TestMod instance() {
        return instance;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static Logger logger() {
        return logger;
    }

    /**
     * Get if the plugin has reloaded
     *
     * @return If the plugin has reloaded
     */
    public static boolean hasReloaded() {
        return RELOADED;
    }

    @Override
    public String name() {
        return TestMod.PROJECT_NAME;
    }

    @Override
    public String id() {
        return TestMod.PROJECT_ID;
    }

    @Override
    public void onEnable() {
        MetaAPI api = MetaAPI.instance();

        logger.info(
                TestMod.PROJECT_NAME
                        + " is running on "
                        + api.platform()
                        + " "
                        + api.version()
                        + ", with "
                        + api.mappings()
                        + " mappings!");
        PluginEvents.DISABLED.register(event -> this.onDisable());

        // Config

        if (!RELOADED) {
            NetworkEvents.REGISTER_CHANNELS.register(
                    event -> {
                        event.register(ResourceKey.of("testmod", "ping"));
                        event.register(ResourceKey.of("testmod", "pong"));
                    });

            // Register listeners
            // CommandEvents.REGISTER_COMMAND.register(
            //         event -> {
            //             event.registerCommand(new PermsTestCommand());
            //         });
            // CommandEvents.REGISTER_BRIGADIER_COMMAND.register(event -> {
            //     Command command = new PermsTestCommand();
            //     BrigadierHelperClass.onRegisterBrigadierCommand(
            //             event, command, command.name(), "ex", "alias2");
            // });

            if (api.side().isServer()) {
                CommandEvents.REGISTER_COMMAND.register(
                        event -> event.registerCommand(new PingPongCommand()));

                PlayerEvents.MESSAGE.register(
                        event -> {
                            String message = event.message();
                            if (message.equals("ping")) {
                                logger.info("Sending packet to player");
                                ((ServerPlayer) event.player())
                                        .sendPacket(
                                                ResourceKey.of("testmod", "ping"),
                                                "Ping".getBytes());
                            }
                        });

                NetworkEvents.C2S_CUSTOM_PACKET.register(
                        event -> {
                            CustomPayloadPacket packet = event.packet();
                            ResourceKey channel = packet.channel();
                            if (channel.equals(ResourceKey.of("testmod", "pong"))) {
                                byte[] data = packet.data();
                                String message = new String(data);
                                // logger.info(Arrays.toString(data));
                                logger.info(
                                        "Received packet on channel "
                                                + channel
                                                + " with message: "
                                                + message);
                                event.player()
                                        .sendMessage("Received packet with message: " + message);
                            }
                        });
            }

            if (api.side().isClient()) {
                NetworkEvents.S2C_CUSTOM_PACKET.register(
                        event -> {
                            try {
                                CustomPayloadPacket packet = event.packet();
                                ResourceKey channel = packet.channel();
                                if (channel.equals(ResourceKey.of("testmod", "ping"))) {
                                    byte[] data = packet.data();
                                    String message = new String(data);
                                    // logger.info(Arrays.toString(data));
                                    logger.info(
                                            "Received packet on channel "
                                                    + channel
                                                    + " with message: "
                                                    + message);

                                    logger.info("Sending packet to server");
                                    event.server()
                                            .sendPacket(
                                                    ResourceKey.of("testmod", "pong"),
                                                    "Pong".getBytes());
                                }
                            } catch (Exception e) {
                                logger.warn("Failed to send packet to server", e);
                            }
                        });
            }
        }

        TestModAPIProvider.register(new TestModAPI("someData"));

        logger.info(PROJECT_NAME + " has been started!");
    }

    @Override
    public void onDisable() {
        // Remove references to objects

        // Unregister API
        TestModAPIProvider.unregister();

        logger.info(PROJECT_NAME + " has been stopped!");
    }

    /** Reload */
    public void reload() {
        RELOADED = true;
        this.onDisable();
        this.onEnable();
        logger.info(PROJECT_NAME + " has been reloaded!");
    }
}
