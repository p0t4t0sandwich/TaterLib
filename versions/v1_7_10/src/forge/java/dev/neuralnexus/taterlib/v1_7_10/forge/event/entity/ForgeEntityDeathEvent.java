/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity.VanillaEntityEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory.WrappedItemStack;

import net.minecraft.entity.ItemEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link EntityDeathEvent}. */
public class ForgeEntityDeathEvent extends VanillaEntityEvent implements EntityDeathEvent {
    private final LivingDeathEvent event;

    public ForgeEntityDeathEvent(LivingDeathEvent event) {
        super(event.entity);
        this.event = event;
    }

    // TODO: Test to see if these work with Forge's event order
    @Override
    public List<ItemStack> drops() {
        if (this.event.entity.capturedDrops == null) {
            return new ArrayList<>();
        }
        return this.event.entity.capturedDrops.stream()
                .map(itemEntity -> new WrappedItemStack(itemEntity.getItemStack()))
                .collect(Collectors.toList());
    }

    @Override
    public void setDrops(List<ItemStack> drops) {
        this.event.entity.capturedDrops.clear();
        this.event.entity.capturedDrops.addAll(
                drops.stream()
                        .map(WrappedItemStack.class::cast)
                        .map(WrappedItemStack::unwrap)
                        .map(
                                itemStack ->
                                        new ItemEntity(
                                                this.event.entity.world,
                                                this.event.entity.x,
                                                this.event.entity.y,
                                                this.event.entity.z,
                                                itemStack))
                        .collect(Collectors.toList()));
    }

    @Override
    public void clearDrops() {
        this.event.entity.capturedDrops.clear();
    }

    // TODO: either use reflection or set up an accessor
    @Override
    public int droppedExp() {
        // protected
        // this.event.entityLiving.getExperiencePoints(
        //     (EntityPlayer) this.event.source.getEntity());
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setDroppedExp(int exp) {
        throw new VersionFeatureNotSupportedException();
    }
}
