package dev.neuralnexus.taterlib.fabric.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class FabricItemMeta implements AbstractItemMeta {
    private final ItemStack itemStack;

    public FabricItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomName();
    }

    @Override
    public String getDisplayName() {
        return itemStack.getName().getString();
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.setCustomName(Text.of(name));
    }

    @Override
    public boolean hasLore() {
        // TODO: Implement
        return false;
    }

    @Override
    public List<String> getLore() {
        // TODO: Implement
        return null;
    }

    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
    }

    @Override
    public boolean hasEnchants() {
        return !itemStack.getEnchantments().isEmpty();
    }

    @Override
    public boolean isUnbreakable() {
        // TODO: Implement
        return false;
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
