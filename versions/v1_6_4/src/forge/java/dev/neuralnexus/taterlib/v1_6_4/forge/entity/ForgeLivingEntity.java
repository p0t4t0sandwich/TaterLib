/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterapi.entity.Nameable;

import net.minecraft.entity.EntityLivingBase;

import org.jspecify.annotations.NonNull;

/** Forge implementation of {@link LivingEntity}. */
public class ForgeLivingEntity extends ForgeEntity
        implements LivingEntity, Damageable, DataHolder, Identifiable, Nameable {
    private final @NonNull EntityLivingBase entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public ForgeLivingEntity(final @NonNull EntityLivingBase entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public @NonNull EntityLivingBase unwrap() {
        return this.entity;
    }
}
