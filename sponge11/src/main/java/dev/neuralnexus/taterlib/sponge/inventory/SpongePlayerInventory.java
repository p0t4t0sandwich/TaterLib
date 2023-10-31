package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

/**
 * Abstracts a Sponge player inventory to an AbstractPlayerInventory.
 */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack[] getArmorContents() {
        EquipmentInventory armor = playerInventory.armor();
        ItemStack[] armorContents = new ItemStack[4];
        armor.peek(EquipmentTypes.HEAD).ifPresent(itemStack -> armorContents[0] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.CHEST).ifPresent(itemStack -> armorContents[1] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.LEGS).ifPresent(itemStack -> armorContents[2] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.FEET).ifPresent(itemStack -> armorContents[3] = new SpongeItemStack(itemStack));
        return armorContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack[] getExtraContents() {
        // TODO: Implement
        return new ItemStack[0];
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack helmet = playerInventory.armor().peek(EquipmentTypes.HEAD).orElse(null);
        return helmet == null ? null : new SpongeItemStack(helmet);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack chestplate = playerInventory.armor().peek(EquipmentTypes.CHEST).orElse(null);
        return chestplate == null ? null : new SpongeItemStack(chestplate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack leggings = playerInventory.armor().peek(EquipmentTypes.LEGS).orElse(null);
        return leggings == null ? null : new SpongeItemStack(leggings);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack boots = playerInventory.armor().peek(EquipmentTypes.FEET).orElse(null);
        return boots == null ? null : new SpongeItemStack(boots);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setArmorContents(ItemStack[] items) {
        EquipmentInventory armor = playerInventory.armor();
        armor.set(EquipmentTypes.HEAD, items[0] == null ? null : ((SpongeItemStack) items[0]).getItemStack());
        armor.set(EquipmentTypes.CHEST, items[1] == null ? null : ((SpongeItemStack) items[1]).getItemStack());
        armor.set(EquipmentTypes.LEGS, items[2] == null ? null : ((SpongeItemStack) items[2]).getItemStack());
        armor.set(EquipmentTypes.FEET, items[3] == null ? null : ((SpongeItemStack) items[3]).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.armor().set(EquipmentTypes.HEAD, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.armor().set(EquipmentTypes.CHEST, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.armor().set(EquipmentTypes.LEGS, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.armor().set(EquipmentTypes.FEET, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItemInOffHand() {
        org.spongepowered.api.item.inventory.ItemStack offHand = playerInventory.armor().peek(EquipmentTypes.OFF_HAND).orElse(null);
        return offHand == null ? null : new SpongeItemStack(offHand);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInOffHand(ItemStack item) {
        playerInventory.armor().set(EquipmentTypes.OFF_HAND, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getHeldItemSlot() {
        // TODO: Implement
        return 0;
    }
}
