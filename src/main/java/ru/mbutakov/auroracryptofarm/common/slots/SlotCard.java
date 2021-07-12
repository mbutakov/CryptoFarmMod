package ru.mbutakov.auroracryptofarm.common.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.GpuItem;

public class SlotCard extends Slot {

	private ContainerBlockPc container;
	private int VidiocardSlot;
    private int slotIndex;
    
	public SlotCard(IInventory inventory, int i, int x, int y, ContainerBlockPc s,int vidiocardslot) {
		super(inventory, i, x, y);
		this.slotIndex = i;
		this.container = s;
		this.VidiocardSlot = vidiocardslot;
	}

	public boolean isItemValid(ItemStack stack) {
		try {
			if(container.getSlot(2).getStack() == null) {
				return false;
			}
			MotherboardItem motherboard = (MotherboardItem) container.getSlot(2).getStack().getItem();
			if(motherboard.getCountVideocard() == 1 && VidiocardSlot > 1) {
				return false;
			}
			if(motherboard.getCountVideocard() == 2 && VidiocardSlot > 2) {
				return false;
			}
		} catch (Exception e) {
		}
		return stack == null || stack.getItem() instanceof GpuItem;
	}
    
}
