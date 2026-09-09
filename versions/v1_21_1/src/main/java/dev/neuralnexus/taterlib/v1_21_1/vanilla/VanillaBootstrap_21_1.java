/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21_1.vanilla;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.HumanEntity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.network.chat.Component;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaLocation;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap_21_1 {
    /** Initializes the Vanilla bootstrap. */
    public static void init() {
        TaterAPI.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);
        DataRegistry.register(Identifiable.class, Entity.class)
                .immutable(Keys.UUID, e -> e::getUUID);
        DataRegistry.register(Damageable.class, LivingEntity.class)
                .mutable(
                        Keys.ABSORPTION,
                        e -> () -> (double) e.getAbsorptionAmount(),
                        e -> v -> e.setAbsorptionAmount(v.floatValue()))
                .mutable(
                        Keys.HEALTH,
                        e -> () -> (double) e.getHealth(),
                        e -> v -> e.setHealth(v.floatValue()))
                .mutable(
                        Keys.MAX_HEALTH,
                        e -> () -> ((LivingEntityBridge) e).bridge$maxHealth(),
                        e -> v -> ((LivingEntityBridge) e).bridge$maxHealth(v));
        DataRegistry.register(Nameable.class, Player.class)
                .immutable(Keys.DISPLAY_NAME, p -> () -> p.getDisplayName().getString())
                .immutable(Keys.NAME, p -> () -> NameAndId.wrap(p.getGameProfile()).name());
        DataRegistry.register(Nameable.class, Entity.class)
                .mutable(
                        Keys.CUSTOM_NAME,
                        e -> () -> e.getCustomName() != null ? e.getCustomName().getString() : null,
                        e ->
                                v -> {
                                    if (v != null) {
                                        e.setCustomName(Component.literal(v));
                                    } else {
                                        e.setCustomName(null);
                                    }
                                });
        DataRegistry.register(Nameable.class, CommandSourceStack.class)
                .immutable(Keys.NAME, s -> () -> s.getDisplayName().getString());
        DataRegistry.register(HumanEntity.class, Player.class)
                .mutable(
                        Keys.GAME_MODE,
                        p ->
                                () ->
                                        GameMode.fromName(
                                                ((ServerPlayer) p)
                                                        .gameMode
                                                        .getGameModeForPlayer()
                                                        .getName()),
                        p -> v -> ((ServerPlayer) p).setGameMode(GameType.byId(v.id())));
    }
}
