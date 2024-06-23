package dev.neuralnexus.taterlib.v1_8_9.forge.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.player.InventoryPlayer;

/** Forge implementation of {@link PlayerInventory}. */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
  private final InventoryPlayer playerInventory;

  /**
   * Constructor.
   *
   * @param playerInventory The Forge player inventory.
   */
  public ForgePlayerInventory(InventoryPlayer playerInventory) {
    super(playerInventory);
    this.playerInventory = playerInventory;
  }

  /** {@inheritDoc} */
  @Override
  public List<ItemStack> armor() {
    return Arrays.stream(playerInventory.armorInventory)
        .map(ForgeItemStack::new)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc} */
  @Override
  public void setArmor(List<ItemStack> armor) {
    for (int i = 0; i < playerInventory.armorInventory.length; i++) {
      playerInventory.armorInventory[i] = ((ForgeItemStack) armor.get(i)).itemStack();
    }
  }

  /** {@inheritDoc} */
  @Override
  public ItemStack offhand() {
    throw new VersionFeatureNotSupportedException();
  }

  /** {@inheritDoc} */
  @Override
  public void setOffhand(ItemStack offhand) {
    throw new VersionFeatureNotSupportedException();
  }

  /** {@inheritDoc} */
  @Override
  public int selectedSlot() {
    return playerInventory.currentItem;
  }
}
