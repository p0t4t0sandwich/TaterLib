/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;

import java.util.List;
import java.util.Optional;

/** Forge implementation of {@link ItemStack}. */
public class ForgeItemStack implements ItemStack, Wrapped<net.minecraft.item.ItemStack> {
    private final net.minecraft.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Forge item stack.
     */
    public ForgeItemStack(net.minecraft.item.ItemStack itemStack) {
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
    public int count() {
        return this.itemStack.stackSize;
    }

    @Override
    public void setCount(int count) {
        this.itemStack.stackSize = count;
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new ForgeItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.hasDisplayName();
    }

    @Override
    public Optional<String> displayName() {
        return Optional.of(this.itemStack.getDisplayName());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.setStackDisplayName(name);
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
        return this.itemStack.isItemEnchanted();
    }

    @Override
    public boolean unbreakable() {
        return this.itemStack.getItem().getHasSubtypes();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        // Reflect to get protected Item#setHasSubtypes(boolean)
        try {
            this.itemStack
                    .getItem()
                    .getClass()
                    .getDeclaredMethod("func_77627_a", boolean.class)
                    .invoke(this.itemStack.getItem(), unbreakable);
        } catch (Exception e) {
            TaterLib.logger().error("Failed to set unbreakable item", e);
        }
    }
}
