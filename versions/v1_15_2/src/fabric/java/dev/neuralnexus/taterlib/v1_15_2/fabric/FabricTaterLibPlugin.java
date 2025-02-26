/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.fabric;

import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

@SuppressWarnings("unused")
public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        VanillaBootstrap.init();

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FABRIC)) {
            // Initialize plugin data
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> TaterLib.stop());

            // Register Fabric API command events
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, dedicated) -> {
                        CommandEvents.REGISTER_COMMAND.invoke(
                                new VanillaCommandRegisterEvent(dispatcher, dedicated));
                        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                new VanillaBrigadierCommandRegisterEvent(dispatcher, dedicated));
                    });

            // Register Fabric API player events
            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, s) ->
                            PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent(handler.player)));
            ServerPlayConnectionEvents.DISCONNECT.register(
                    (handler, s) ->
                            PlayerEvents.LOGOUT.invoke(
                                    new VanillaPlayerLogoutEvent(handler.player)));

            // Register Fabric API server events
            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {}));
        }
    }
}
