/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16.vanilla.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Vanilla implementation of {@link ItemStack} */
public class VanillaItemStack implements ItemStack, Wrapped<net.minecraft.world.item.ItemStack> {
    private final net.minecraft.world.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Vanilla item stack.
     */
    public VanillaItemStack(net.minecraft.world.item.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new net.minecraft.world.item.ItemStack(Items.AIR) : itemStack;
    }

    @Override
    public net.minecraft.world.item.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) Registry.ITEM.getKey(this.itemStack.getItem());
    }

    @Override
    public int count() {
        return this.itemStack.getCount();
    }

    @Override
    public void setCount(int count) {
        this.itemStack.setCount(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new VanillaItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.hasCustomHoverName();
    }

    @Override
    public Optional<String> displayName() {
        if (!this.itemStack.hasCustomHoverName()) return Optional.empty();
        return Optional.of(this.itemStack.getDisplayName().getString());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.setHoverName(Component.nullToEmpty(name));
    }

    @Override
    public boolean hasLore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<String> lore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean hasEnchants() {
        return this.itemStack.isEnchanted();
    }

    @Override
    public boolean unbreakable() {
        Objects.requireNonNull(this.itemStack.getTag());
        return this.itemStack.hasTag() && this.itemStack.getTag().getBoolean("Unbreakable");
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.itemStack.getOrCreateTagElement("Unbreakable").putBoolean("Unbreakable", unbreakable);
    }
}
