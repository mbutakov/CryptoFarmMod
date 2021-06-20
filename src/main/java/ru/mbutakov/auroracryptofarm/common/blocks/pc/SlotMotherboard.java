package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.VideoCard;

public class SlotMotherboard extends Slot {

	private ContainerBlockPc container;
	
	public SlotMotherboard(IInventory inventory, int i, int x, int y, ContainerBlockPc s) {
		super(inventory, i, x, y);
		this.container = s;
	}

	public boolean isItemValid(ItemStack stack) {
		ItemStack stackSlotCpu = container.getSlot(0).getStack();
		if(stackSlotCpu != null) {
			return false;
		}
		ItemStack stackSlotVidiocard = container.getSlot(1).getStack();
		if(stackSlotVidiocard != null) {
			return false;
		}
		ItemStack stackSlotVidiocard2 = container.getSlot(3).getStack();
		if(stackSlotVidiocard != null) {
			return false;
		}
		ItemStack stackSlotVidiocard3 = container.getSlot(4).getStack();
		if(stackSlotVidiocard != null) {
			return false;
		}
		return stack == null || stack.getItem() instanceof MotherboardItem;
	}
}
