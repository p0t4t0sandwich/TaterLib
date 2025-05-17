/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.living.LivingEntity;

import java.util.List;

/** Vanilla implementation of {@link EntityDeathEvent}. */
public class VanillaEntityDeathEvent extends VanillaEntityEvent implements EntityDeathEvent {
    private final LivingEntity living;
    private final DamageSource source;

    // TODO: implement some mixins to handle these features, somehow all in one event
    // Maybe handle with functional interfaces so Forge's impl can be stitched in
    public VanillaEntityDeathEvent(Entity entity, DamageSource source) {
        super(entity);
        this.living = (LivingEntity) entity;
        this.source = source;
    }

    @Override
    public List<ItemStack> drops() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setDrops(List<ItemStack> drops) {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void clearDrops() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public int droppedExp() {
        // protected
        // this.event.entityLiving.getExperiencePoints(
        //     (EntityPlayer) this.event.source.entity());
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setDroppedExp(int exp) {
        throw new VersionFeatureNotSupportedException();
    }
}
