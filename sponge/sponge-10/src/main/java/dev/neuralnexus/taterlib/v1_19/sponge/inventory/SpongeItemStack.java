package dev.neuralnexus.taterlib.v1_19.sponge.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.registry.Registry;

import java.util.ArrayList;
import java.util.List;

/** Sponge implementation of {@link ItemStack}. */
public class SpongeItemStack implements ItemStack {
    private final org.spongepowered.api.item.inventory.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemStack(org.spongepowered.api.item.inventory.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Sponge item stack.
     *
     * @return The Sponge item stack.
     */
    public org.spongepowered.api.item.inventory.ItemStack itemStack() {
        return itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        Registry<ItemType> itemTypeRegistry = ItemTypes.registry();
        return itemTypeRegistry.valueKey(itemStack.type()).asString();
    }

    /** {@inheritDoc} */
    @Override
    public int count() {
        return itemStack.quantity();
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(int count) {
        itemStack.setQuantity(count);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack clone() {
        return new SpongeItemStack(itemStack.copy());
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.get(Keys.CUSTOM_NAME).isPresent();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        if (!itemStack.get(Keys.CUSTOM_NAME).isPresent()) {
            return null;
        }
        return itemStack.get(Keys.CUSTOM_NAME).get().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLore() {
        return itemStack.get(Keys.LORE).isPresent();
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public void setLore(List<String> list) {
        List<Component> lore = new ArrayList<>();
        for (String string : list) {
            lore.add(Component.text(string));
        }
        itemStack.offer(Keys.LORE, lore);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasEnchants() {
        return itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        return itemStack.get(Keys.IS_UNBREAKABLE).isPresent();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.offer(Keys.IS_UNBREAKABLE, unbreakable);
    }
}
