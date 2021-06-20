package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.VideoCard;

public class SlotCpu extends Slot {

	private ContainerBlockPc container;
	
	public SlotCpu(IInventory inventory, int i, int x, int y, ContainerBlockPc s) {
		super(inventory, i, x, y);
		this.container = s;
	}

	public boolean isItemValid(ItemStack stack) {
		if(stack == null || stack.getItem() instanceof CpuItem) {
			ItemStack stackSlot = container.getSlot(2).getStack();
			if(stackSlot == null) {
				return false;
			}
			MotherboardItem motherBoard = (MotherboardItem)stackSlot.getItem();
			if(motherBoard.getChip() != ((CpuItem)stack.getItem()).getChip()) {
				return false;
			}
		}else {
			return false;
		}
		return true;
	}
}
