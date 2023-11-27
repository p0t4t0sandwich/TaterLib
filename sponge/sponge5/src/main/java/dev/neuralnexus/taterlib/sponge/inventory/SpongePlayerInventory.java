package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;

import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

/** Abstracts a Sponge player inventory to an AbstractPlayerInventory. */
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
    public ItemStack[] getArmorContents() {
        EquipmentInventory armor = playerInventory.getEquipment();
        ItemStack[] armorContents = new ItemStack[4];
        armor.peek(EquipmentTypes.HEADWEAR)
                .ifPresent(itemStack -> armorContents[0] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.CHESTPLATE)
                .ifPresent(itemStack -> armorContents[1] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.LEGGINGS)
                .ifPresent(itemStack -> armorContents[2] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.BOOTS)
                .ifPresent(itemStack -> armorContents[3] = new SpongeItemStack(itemStack));
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        EquipmentInventory armor = playerInventory.getEquipment();
        if (items[0] != null) {
            armor.set(EquipmentTypes.HEADWEAR, ((SpongeItemStack) items[0]).getItemStack());
        }
        if (items[1] != null) {
            armor.set(EquipmentTypes.CHESTPLATE, ((SpongeItemStack) items[1]).getItemStack());
        }
        if (items[2] != null) {
            armor.set(EquipmentTypes.LEGGINGS, ((SpongeItemStack) items[2]).getItemStack());
        }
        if (items[3] != null) {
            armor.set(EquipmentTypes.BOOTS, ((SpongeItemStack) items[3]).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getExtraContents() {
        // TODO: Implement
        return new ItemStack[0];
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack helmet =
                playerInventory.getEquipment().peek(EquipmentTypes.HEADWEAR).orElse(null);
        return helmet == null ? null : new SpongeItemStack(helmet);
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        if (item != null) {
            playerInventory
                    .getEquipment()
                    .set(EquipmentTypes.HEADWEAR, ((SpongeItemStack) item).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack chestplate =
                playerInventory.getEquipment().peek(EquipmentTypes.CHESTPLATE).orElse(null);
        return chestplate == null ? null : new SpongeItemStack(chestplate);
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        if (item != null) {
            playerInventory
                    .getEquipment()
                    .set(EquipmentTypes.CHESTPLATE, ((SpongeItemStack) item).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack leggings =
                playerInventory.getEquipment().peek(EquipmentTypes.LEGGINGS).orElse(null);
        return leggings == null ? null : new SpongeItemStack(leggings);
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        if (item != null) {
            playerInventory
                    .getEquipment()
                    .set(EquipmentTypes.LEGGINGS, ((SpongeItemStack) item).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack boots =
                playerInventory.getEquipment().peek(EquipmentTypes.BOOTS).orElse(null);
        return boots == null ? null : new SpongeItemStack(boots);
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        if (item != null) {
            playerInventory
                    .getEquipment()
                    .set(EquipmentTypes.BOOTS, ((SpongeItemStack) item).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
        //        ItemStack offHand =
        // playerInventory.getEquipment().peek(EquipmentTypes.OFF_HAND).orElse(null);
        //        return offHand == null ? null : new SpongeItemStack(offHand);
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        // TODO: Implement
        //        if (item != null) {
        //            playerInventory.getEquipment().set(EquipmentTypes.OFF_HAND, ((SpongeItemStack)
        // item).getItemStack());
        //        }
    }

    /** {@inheritDoc} */
    @Override
    public int getHeldItemSlot() {
        // TODO: Implement
        return 0;
    }
}
