/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

/** Vanilla implementation of {@link EntityDeathEvent}. */
public class VanillaEntityDeathEvent extends VanillaEntityEvent
        implements EntityDeathEvent, LivingEntityBridge {
    private final DamageSource source;

    public VanillaEntityDeathEvent(Entity entity, DamageSource source) {
        super(entity);
        this.source = source;
    }

    @Override
    public List<ItemStack> drops() {
        return new ArrayList<>();
    }

    @Override
    public void setDrops(List<ItemStack> drops) {}

    @Override
    public void clearDrops() {}

    @Override
    public int droppedExp() {
        if (source.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) source.getEntity();
            if (entity.getLastHurtByMob() != null && entity.getLastHurtByMob() instanceof Player) {
                return this.bridge$getExperienceReward(entity, (Player) entity.getLastHurtByMob());
            }
        }
        return 0;
    }

    @Override
    public void setDroppedExp(int exp) {}
}
