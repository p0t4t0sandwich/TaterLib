/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21_4.neoforge;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_20_2.neoforge.NeoForgeEventListener_20_2;
import dev.neuralnexus.taterlib.v1_21_1.vanilla.VanillaBootstrap_21_1;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        VanillaBootstrap_21_1.init();

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.NEOFORGE)) {
            NeoForgeEventListener_20_2.init();

            // TODO: See if the cancellation wrapper works as intended
            NeoForge.EVENT_BUS.<LivingDamageEvent.Pre>addListener(
                    event ->
                            EntityEvents.DAMAGE.invoke(
                                    new VanillaEntityDamageEvent(
                                            event.getEntity(),
                                            event.getSource(),
                                            event.getNewDamage(),
                                            new Cancellable() {
                                                @Override
                                                public boolean cancelled() {
                                                    return event.getNewDamage() <= 0;
                                                }

                                                @Override
                                                public void setCancelled(boolean cancelled) {
                                                    event.setNewDamage(
                                                            cancelled ? 0 : event.getNewDamage());
                                                }
                                            })));

            NeoForge.EVENT_BUS.<MobSpawnEvent.PositionCheck>addListener(
                    event ->
                            EntityEvents.SPAWN.invoke(
                                    new VanillaEntitySpawnEvent(
                                            event.getEntity(),
                                            new Cancellable() {
                                                @Override
                                                public boolean cancelled() {
                                                    return event.getResult()
                                                            == MobSpawnEvent.PositionCheck.Result
                                                                    .FAIL;
                                                }

                                                @Override
                                                public void setCancelled(boolean cancelled) {
                                                    event.setResult(
                                                            cancelled
                                                                    ? MobSpawnEvent.PositionCheck
                                                                            .Result.FAIL
                                                                    : MobSpawnEvent.PositionCheck
                                                                            .Result.SUCCEED);
                                                }
                                            })));
        }
    }
}
