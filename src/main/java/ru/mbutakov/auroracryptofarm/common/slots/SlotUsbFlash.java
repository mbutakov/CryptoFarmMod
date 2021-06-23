package ru.mbutakov.auroracryptofarm.common.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.FanItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;

public class SlotUsbFlash extends Slot {
	
	private ContainerBlockPc container;
	
	public SlotUsbFlash(IInventory inventory, int i, int x, int y, ContainerBlockPc s) {
		super(inventory, i, x, y);
		this.container = s;
	}
	
	public boolean isItemValid(ItemStack stack) {
		if(stack == null || stack.getItem() instanceof UsbflashItem) {
			return true;
		}
		return false;
	}
}
