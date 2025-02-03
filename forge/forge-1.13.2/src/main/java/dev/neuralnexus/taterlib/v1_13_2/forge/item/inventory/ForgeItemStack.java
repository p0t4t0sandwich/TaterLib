/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.v1_13_2.forge.resource.ForgeResourceKey;

import net.minecraft.init.Items;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextComponentString;

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
        this.itemStack =
                itemStack == null ? new net.minecraft.item.ItemStack(Items.AIR) : itemStack;
    }

    @Override
    public net.minecraft.item.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceKey type() {
        return new ForgeResourceKey(IRegistry.field_212630_s.getKey(this.itemStack.getItem()));
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
        return new ForgeItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.hasDisplayName();
    }

    @Override
    public Optional<String> displayName() {
        if (!itemStack.hasDisplayName()) return Optional.empty();
        return Optional.of(this.itemStack.getDisplayName().getString());
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.setDisplayName(new TextComponentString(name));
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
        return itemStack.isEnchanted();
    }

    @Override
    public boolean unbreakable() {
        return itemStack.isDamageable();
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
