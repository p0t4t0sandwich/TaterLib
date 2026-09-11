/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge;

import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.HumanEntity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterlib.v1_8_9.sponge.block.SpongeBlock;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.SpongeLivingEntity;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.item.inventory.entity.HumanInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SpongeBootstrap {
    public static void init() {
        SpongeFactories.blockFromEvent =
                event -> {
                    List<Location<World>> locations = event.getLocations();
                    if (!locations.isEmpty()) {
                        Location<World> location = locations.get(0);
                        return new SpongeBlock(location);
                    }
                    return null;
                };

        SpongeFactories.itemStackType =
                itemStack -> Identifier.of(itemStack.getItem().getType().getName());
        SpongeFactories.nilItemStack = () -> null;

        SpongeFactories.equipmentFromInventory =
                inventory -> {
                    AtomicReference<EquipmentInventory> armor = new AtomicReference<>();
                    inventory.forEach(
                            inv -> {
                                if (inv instanceof EquipmentInventory) {
                                    armor.set((EquipmentInventory) inv);
                                }
                            });
                    if (armor.get() == null) {
                        throw new IllegalStateException(
                                "Player inventory does not contain armor inventory");
                    }
                    return armor.get();
                };

        SpongeFactories.inventoryOffHand =
                inventory -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.inventorySetOffHand =
                (inventory, itemStack) -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.inventorySelectedSlot =
                inventory -> {
                    if (inventory instanceof HumanInventory) {
                        return ((HumanInventory) inventory).getHotbar().getSelectedSlotIndex();
                    }
                    return -1;
                };

        SpongeFactories.worldBiome =
                (world, pos) ->
                        Identifier.of(world.getBiome(pos.blockX(), pos.blockZ()).getId());

        SpongeFactories.absorptionAmount =
                entity -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.setAbsorptionAmount =
                (entity, amount) -> {
                    throw new VersionFeatureNotSupportedException();
                };

        SpongeFactories.keepInventory =
                event -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.setKeepInventory =
                (event, keepInventory) -> {
                    throw new VersionFeatureNotSupportedException();
                };

        DataRegistry.register(Identifiable.class, Entity.class)
                .immutable(Keys.UUID, e -> e::getUniqueId);
        DataRegistry.register(Damageable.class, Living.class)
                .mutable(
                        Keys.ABSORPTION,
                        e -> () -> SpongeFactories.absorptionAmount.get(e),
                        e -> v -> SpongeFactories.setAbsorptionAmount.set(e, v))
                .mutable(
                        Keys.HEALTH,
                        e -> () -> SpongeLivingEntity.helper$health(e),
                        e -> v -> SpongeLivingEntity.helper$health(e, v))
                .mutable(
                        Keys.MAX_HEALTH,
                        e -> () -> SpongeLivingEntity.helper$maxHealth(e),
                        e -> v -> SpongeLivingEntity.helper$maxHealth(e, v))
                .action(
                        Keys.DAMAGE_GENERIC,
                        e ->
                                d -> {
                                    e.damage(d, DamageSources.GENERIC);
                                    return null;
                                })
                .action(
                        Keys.DAMAGE_ENTITY,
                        e ->
                                (d, src) -> {
                                    e.damage(
                                            d,
                                            EntityDamageSource.builder()
                                                    .type(DamageTypes.ATTACK)
                                                    .entity(((SpongeLivingEntity) src).unwrap())
                                                    .build());
                                    return null;
                                });
        DataRegistry.register(Nameable.class, Player.class)
                .immutable(
                        Keys.DISPLAY_NAME,
                        p -> () -> p.getDisplayNameData().displayName().get().toPlain())
                .immutable(Keys.NAME, p -> p::getName);
        DataRegistry.register(Nameable.class, Entity.class)
                .mutable(
                        Keys.CUSTOM_NAME,
                        e ->
                                () -> {
                                    if (e.get(org.spongepowered.api.data.key.Keys.DISPLAY_NAME)
                                                    .isEmpty()
                                            || e.get(
                                                            org.spongepowered.api.data.key.Keys
                                                                    .CUSTOM_NAME_VISIBLE)
                                                    .isEmpty()) {
                                        return null;
                                    }
                                    return e.get(org.spongepowered.api.data.key.Keys.DISPLAY_NAME)
                                            .get()
                                            .toString();
                                },
                        e ->
                                v -> {
                                    if (v != null) {
                                        e.offer(
                                                org.spongepowered.api.data.key.Keys.DISPLAY_NAME,
                                                Text.of(v));
                                        e.offer(
                                                org.spongepowered.api.data.key.Keys
                                                        .CUSTOM_NAME_VISIBLE,
                                                true);
                                    } else {
                                        e.offer(
                                                org.spongepowered.api.data.key.Keys.DISPLAY_NAME,
                                                Text.EMPTY);
                                        e.offer(
                                                org.spongepowered.api.data.key.Keys
                                                        .CUSTOM_NAME_VISIBLE,
                                                false);
                                    }
                                });
        DataRegistry.register(Identifiable.class, CommandSource.class)
                .immutable(
                        Keys.UUID,
                        s ->
                                () -> {
                                    if (s instanceof Player p) return p.getUniqueId();
                                    return Notifiable.NIL_UUID;
                                });
        DataRegistry.register(Nameable.class, CommandSource.class)
                .immutable(Keys.NAME, s -> s::getName);
        DataRegistry.register(HumanEntity.class, Player.class)
                .mutable(
                        Keys.GAME_MODE,
                        p ->
                                () ->
                                        GameMode.fromName(
                                                p.get(org.spongepowered.api.data.key.Keys.GAME_MODE)
                                                        .orElseThrow()
                                                        .toString()),
                        p ->
                                v -> {
                                    switch (v) {
                                        case CREATIVE:
                                            p.offer(
                                                    org.spongepowered.api.data.key.Keys.GAME_MODE,
                                                    GameModes.CREATIVE);
                                            break;
                                        case SURVIVAL:
                                            p.offer(
                                                    org.spongepowered.api.data.key.Keys.GAME_MODE,
                                                    GameModes.SURVIVAL);
                                            break;
                                        case ADVENTURE:
                                            p.offer(
                                                    org.spongepowered.api.data.key.Keys.GAME_MODE,
                                                    GameModes.ADVENTURE);
                                            break;
                                        case SPECTATOR:
                                            p.offer(
                                                    org.spongepowered.api.data.key.Keys.GAME_MODE,
                                                    GameModes.SPECTATOR);
                                            break;
                                    }
                                });
    }
}
