/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/** Vanilla implementation of {@link EntityDeathEvent}. */
public class VanillaEntityDeathEvent extends VanillaEntityEvent implements EntityDeathEvent {
    private final Entity entity;
    private final DamageSource source;

    public VanillaEntityDeathEvent(Entity entity, DamageSource source) {
        super(entity);
        this.entity = entity;
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
        return source.getEntity() instanceof LivingEntity
                ? ((LivingEntity) source.getEntity())
                        .getExperienceReward((ServerLevel) entity.level(), entity)
                : 0;
    }

    @Override
    public void setDroppedExp(int exp) {}
}
