/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.sponge.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.ItemTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link ItemStack}. */
public class SpongeItemStack implements ItemStack {
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

    /**
     * Getter for the Sponge item stack.
     *
     * @return The Sponge item stack.
     */
    public org.spongepowered.api.item.inventory.ItemStack itemStack() {
        return itemStack;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) (Object) ItemTypes.registry().valueKey(itemStack.type());
    }

    @Override
    public int count() {
        return itemStack.quantity();
    }

    @Override
    public void setCount(int count) {
        itemStack.setQuantity(count);
    }

    @Override
    public ItemStack clone() {
        return new SpongeItemStack(itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return itemStack.get(Keys.CUSTOM_NAME).isPresent();
    }

    @Override
    public Optional<String> displayName() {
        if (!itemStack.get(Keys.CUSTOM_NAME).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(itemStack.get(Keys.CUSTOM_NAME).get().toString());
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    @Override
    public boolean hasLore() {
        return itemStack.get(Keys.LORE).isPresent();
    }

    @Override
    public List<String> lore() {
        if (!itemStack.get(Keys.LORE).isPresent()) {
            return null;
        }
        List<Component> componentLore = itemStack.get(Keys.LORE).get();
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
        itemStack.offer(Keys.LORE, lore);
    }

    @Override
    public boolean hasEnchants() {
        return itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    @Override
    public boolean unbreakable() {
        return itemStack.get(Keys.IS_UNBREAKABLE).isPresent();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.offer(Keys.IS_UNBREAKABLE, unbreakable);
    }
}
