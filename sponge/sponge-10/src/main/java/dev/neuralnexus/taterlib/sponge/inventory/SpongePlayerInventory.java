package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

/** Sponge implementation of {@link PlayerInventory}. */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(
            org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] armorContents() {
        EquipmentInventory armor = playerInventory.armor();
        ItemStack[] armorContents = new ItemStack[4];
        armor.peek(EquipmentTypes.HEAD)
                .ifPresent(itemStack -> armorContents[0] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.CHEST)
                .ifPresent(itemStack -> armorContents[1] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.LEGS)
                .ifPresent(itemStack -> armorContents[2] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.FEET)
                .ifPresent(itemStack -> armorContents[3] = new SpongeItemStack(itemStack));
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        EquipmentInventory armor = playerInventory.armor();
        armor.set(
                EquipmentTypes.HEAD,
                items[0] == null ? null : ((SpongeItemStack) items[0]).itemStack());
        armor.set(
                EquipmentTypes.CHEST,
                items[1] == null ? null : ((SpongeItemStack) items[1]).itemStack());
        armor.set(
                EquipmentTypes.LEGS,
                items[2] == null ? null : ((SpongeItemStack) items[2]).itemStack());
        armor.set(
                EquipmentTypes.FEET,
                items[3] == null ? null : ((SpongeItemStack) items[3]).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] extraContents() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack helmet() {
        org.spongepowered.api.item.inventory.ItemStack helmet =
                playerInventory.armor().peek(EquipmentTypes.HEAD).orElse(null);
        return helmet == null ? null : new SpongeItemStack(helmet);
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory
                .armor()
                .set(
                        EquipmentTypes.HEAD,
                        item == null ? null : ((SpongeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack chestplate() {
        org.spongepowered.api.item.inventory.ItemStack chestplate =
                playerInventory.armor().peek(EquipmentTypes.CHEST).orElse(null);
        return chestplate == null ? null : new SpongeItemStack(chestplate);
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory
                .armor()
                .set(
                        EquipmentTypes.CHEST,
                        item == null ? null : ((SpongeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack leggings() {
        org.spongepowered.api.item.inventory.ItemStack leggings =
                playerInventory.armor().peek(EquipmentTypes.LEGS).orElse(null);
        return leggings == null ? null : new SpongeItemStack(leggings);
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory
                .armor()
                .set(
                        EquipmentTypes.LEGS,
                        item == null ? null : ((SpongeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack boots() {
        org.spongepowered.api.item.inventory.ItemStack boots =
                playerInventory.armor().peek(EquipmentTypes.FEET).orElse(null);
        return boots == null ? null : new SpongeItemStack(boots);
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory
                .armor()
                .set(
                        EquipmentTypes.FEET,
                        item == null ? null : ((SpongeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack item(String equipmentSlot) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack itemInMainHand() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack itemInOffHand() {
        org.spongepowered.api.item.inventory.ItemStack offHand =
                playerInventory.armor().peek(EquipmentTypes.OFF_HAND).orElse(null);
        return offHand == null ? null : new SpongeItemStack(offHand);
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        playerInventory
                .armor()
                .set(
                        EquipmentTypes.OFF_HAND,
                        item == null ? null : ((SpongeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int heldItemSlot() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
