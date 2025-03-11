/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.item.ItemStackBridge;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Vanilla implementation of {@link ItemStack}. */
public class WrappedItemStack implements ItemStack, Wrapped<net.minecraft.item.ItemStack> {
    private final net.minecraft.item.ItemStack itemStack;

    public WrappedItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public net.minecraft.item.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        // TODO: Find item registry
        return ResourceKey.of(this.itemStack.getItem().toString());
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public int count() {
        return ((ItemStackBridge) (Object) this.itemStack).bridge$size();
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void setCount(int count) {
        ((ItemStackBridge) (Object) this.itemStack).bridge$setSize(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new WrappedItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.hasCustomHoverName();
    }

    @Override
    public Optional<String> displayName() {
        return Optional.of(this.itemStack.getHoverName());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.setHoverName(name);
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
        return this.itemStack.hasEnchantments();
    }

    @Override
    public boolean unbreakable() {
        Objects.requireNonNull(this.itemStack.getNbt());
        return this.itemStack.hasNbt() && this.itemStack.getNbt().getBoolean("Unbreakable");
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        Objects.requireNonNull(this.itemStack.getNbt());
        this.itemStack.getNbt().getCompound("Unbreakable").putBoolean("Unbreakable", unbreakable);
    }
}
