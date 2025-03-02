/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.neoforge;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.utils.neoforge.event.NeoForgeCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntitySpawnEvent;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        VanillaBootstrap.init();

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.NEOFORGE)) {
            NeoForgeEventListener_20_2.init();

            NeoForge.EVENT_BUS.<LivingDamageEvent>addListener(
                    event ->
                            EntityEvents.DAMAGE.invoke(
                                    new VanillaEntityDamageEvent(
                                            event.getEntity(),
                                            event.getSource(),
                                            event.getAmount(),
                                            new NeoForgeCancellableEventWrapper(event))));

            NeoForge.EVENT_BUS.<MobSpawnEvent.FinalizeSpawn>addListener(
                    event ->
                            EntityEvents.SPAWN.invoke(
                                    new VanillaEntitySpawnEvent(
                                            event.getEntity(),
                                            new NeoForgeCancellableEventWrapper(event))));
        }
    }
}
