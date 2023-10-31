package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

/**
 * Abstracts a Forge item meta to an AbstractItemMeta.
 */
public class ForgeItemMeta implements ItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Forge item stack.
     */
    public ForgeItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasDisplayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return itemStack.getDisplayName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDisplayName(String name) {
        itemStack.setDisplayName(new StringTextComponent(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasLore() {
        // TODO: Implement
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getLore() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasEnchants() {
        return itemStack.isEnchanted();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isUnbreakable() {
        return itemStack.isDamageable();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
