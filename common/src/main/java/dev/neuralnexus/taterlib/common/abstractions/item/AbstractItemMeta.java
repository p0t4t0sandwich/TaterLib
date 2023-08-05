package dev.neuralnexus.taterlib.common.abstractions.item;

import java.util.List;

public interface AbstractItemMeta {
    boolean hasDisplayName();
    String getDisplayName();
    void setDisplayName(String name);
    boolean hasLore();
    List<String> getLore();
    void setLore(List<String> lore);
    boolean hasEnchants();
    // hasEnchant(Enchantment ench)
    // getEnchantLevel(Enchantment ench)
    // getEnchants() -> Map<Enchantment, Integer>
    // addEnchant(Enchantment ench, int level, boolean ignoreRestrictions)
    // removeEnchant(Enchantment ench)
    boolean isUnbreakable();
    void setUnbreakable(boolean unbreakable);
}
