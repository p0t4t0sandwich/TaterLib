/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21.fabric;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_21.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_21.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.player.VanillaPlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.server.VanillaServerStartedEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.server.VanillaServerStartingEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.server.VanillaServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.server.VanillaServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.server.VanillaServer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        VanillaBootstrap.init();
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        start();
        TaterAPIProvider.api(Platform.FABRIC)
                .ifPresent(api -> api.setServer(VanillaServer::instance));

        if (TaterAPIProvider.isPrimaryPlatform(Platform.FABRIC)) {
            ServerLifecycleEvents.SERVER_STARTING.register(VanillaServer::setServer);
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> stop());

            // Register Fabric API events
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, registryAccess, environment) -> {
                        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                new VanillaBrigadierCommandRegisterEvent(dispatcher, environment));
                        // Sponge has its own, nicer simple command system
                        if (!TaterAPIProvider.platform().isSpongeBased()) {
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new VanillaCommandRegisterEvent(dispatcher, environment));
                        }
                    });

            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, s) ->
                            PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent(handler.player)));
            ServerPlayConnectionEvents.DISCONNECT.register(
                    (handler, s) ->
                            PlayerEvents.LOGOUT.invoke(
                                    new VanillaPlayerLogoutEvent(handler.player)));

            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> ServerEvents.STARTING.invoke(new VanillaServerStartingEvent(s)));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new VanillaServerStartedEvent(s)));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new VanillaServerStoppingEvent(s)));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent(s)));
        }
    }
}
