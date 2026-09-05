/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import org.jspecify.annotations.NonNull;

/** Bukkit implementation of {@link LivingEntity}. */
public class BukkitLivingEntity extends BukkitEntity
        implements LivingEntity, Damageable, DataHolder {
    private final org.bukkit.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The Bukkit entity.
     */
    public BukkitLivingEntity(final org.bukkit.entity.@NonNull LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public org.bukkit.entity.@NonNull LivingEntity unwrap() {
        return this.entity;
    }

    @Override
    public void damage(final double amount) {
        this.entity.damage((int) amount);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.damage((int) amount, ((BukkitEntity) source).unwrap());
    }
}
