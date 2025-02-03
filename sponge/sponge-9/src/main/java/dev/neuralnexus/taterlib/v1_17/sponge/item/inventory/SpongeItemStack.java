/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17.sponge.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.ItemTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link ItemStack}. */
public class SpongeItemStack
        implements ItemStack, Wrapped<org.spongepowered.api.item.inventory.ItemStack> {
    private final org.spongepowered.api.item.inventory.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemStack(org.spongepowered.api.item.inventory.ItemStack itemStack) {
        this.itemStack =
                itemStack == null
                        ? org.spongepowered.api.item.inventory.ItemStack.of(ItemTypes.AIR)
                        : itemStack;
    }

    @Override
    public org.spongepowered.api.item.inventory.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) ItemTypes.registry().valueKey(this.itemStack.type());
    }

    @Override
    public int count() {
        return this.itemStack.quantity();
    }

    @Override
    public void setCount(int count) {
        this.itemStack.setQuantity(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new SpongeItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.get(Keys.CUSTOM_NAME).isPresent();
    }

    @Override
    public Optional<String> displayName() {
        if (!this.itemStack.get(Keys.CUSTOM_NAME).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(this.itemStack.get(Keys.CUSTOM_NAME).get().toString());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    @Override
    public boolean hasLore() {
        return this.itemStack.get(Keys.LORE).isPresent();
    }

    @Override
    public List<String> lore() {
        if (!this.itemStack.get(Keys.LORE).isPresent()) {
            return null;
        }
        List<Component> componentLore = this.itemStack.get(Keys.LORE).get();
        List<String> lore = new ArrayList<>();
        for (Component component : componentLore) {
            lore.add(component.toString());
        }
        return lore;
    }

    @Override
    public void setLore(List<String> list) {
        List<Component> lore = new ArrayList<>();
        for (String string : list) {
            lore.add(Component.text(string));
        }
        this.itemStack.offer(Keys.LORE, lore);
    }

    @Override
    public boolean hasEnchants() {
        return this.itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    @Override
    public boolean unbreakable() {
        return this.itemStack.get(Keys.IS_UNBREAKABLE).isPresent();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.itemStack.offer(Keys.IS_UNBREAKABLE, unbreakable);
    }
}
