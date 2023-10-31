package dev.neuralnexus.taterlib.sponge.player;

import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import dev.neuralnexus.taterlib.common.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.sponge.inventory.SpongeInventory;
import dev.neuralnexus.taterlib.sponge.inventory.SpongeItemStack;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

/**
 * Abstracts a Sponge player inventory to an AbstractPlayerInventory.
 */
public class SpongePlayerInventory extends SpongeInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getArmorContents() {
        EquipmentInventory armor = playerInventory.getEquipment();
        AbstractItemStack[] armorContents = new AbstractItemStack[4];
        armor.peek(EquipmentTypes.HEADWEAR).ifPresent(itemStack -> armorContents[0] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.CHESTPLATE).ifPresent(itemStack -> armorContents[1] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.LEGGINGS).ifPresent(itemStack -> armorContents[2] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.BOOTS).ifPresent(itemStack -> armorContents[3] = new SpongeItemStack(itemStack));
        return armorContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getExtraContents() {
        // TODO: Implement
        return new AbstractItemStack[0];
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getHelmet() {
        ItemStack helmet = playerInventory.getEquipment().peek(EquipmentTypes.HEADWEAR).orElse(null);
        return helmet == null ? null : new SpongeItemStack(helmet);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getChestplate() {
        ItemStack chestplate = playerInventory.getEquipment().peek(EquipmentTypes.CHESTPLATE).orElse(null);
        return chestplate == null ? null : new SpongeItemStack(chestplate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getLeggings() {
        ItemStack leggings = playerInventory.getEquipment().peek(EquipmentTypes.LEGGINGS).orElse(null);
        return leggings == null ? null : new SpongeItemStack(leggings);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getBoots() {
        ItemStack boots = playerInventory.getEquipment().peek(EquipmentTypes.BOOTS).orElse(null);
        return boots == null ? null : new SpongeItemStack(boots);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String equipmentSlot, AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setArmorContents(AbstractItemStack[] items) {
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

    /**
     * @inheritDoc
     */
    @Override
    public void setExtraContents(AbstractItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setHelmet(AbstractItemStack item) {
        if (item != null) {
            playerInventory.getEquipment().set(EquipmentTypes.HEADWEAR, ((SpongeItemStack) item).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(AbstractItemStack item) {
        if (item != null) {
            playerInventory.getEquipment().set(EquipmentTypes.CHESTPLATE, ((SpongeItemStack) item).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(AbstractItemStack item) {
        if (item != null) {
            playerInventory.getEquipment().set(EquipmentTypes.LEGGINGS, ((SpongeItemStack) item).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(AbstractItemStack item) {
        if (item != null) {
            playerInventory.getEquipment().set(EquipmentTypes.BOOTS, ((SpongeItemStack) item).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInMainHand(AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInOffHand() {
        ItemStack offHand = playerInventory.getEquipment().peek(EquipmentTypes.OFF_HAND).orElse(null);
        return offHand == null ? null : new SpongeItemStack(offHand);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInOffHand(AbstractItemStack item) {
        if (item != null) {
            playerInventory.getEquipment().set(EquipmentTypes.OFF_HAND, ((SpongeItemStack) item).getItemStack());
        }
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
