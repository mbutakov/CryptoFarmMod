package ru.mbutakov.auroracryptofarm.common.slots;

import lombok.Setter;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.GpuItem;

@Setter
public class SlotCpu extends Slot {

	private ContainerBlockPc container;
	
	public SlotCpu(IInventory inventory, int i, int x, int y, ContainerBlockPc s) {
		super(inventory, i, x, y);
		this.container = s;
	}

	public boolean isItemValid(ItemStack stack) {
		try {
			if(stack == null || stack.getItem() instanceof CpuItem) {
				ItemStack stackSlot = container.getSlot(2).getStack();
				if(stackSlot == null) {
					return false;
				}
				if(stackSlot.getItem() instanceof MotherboardItem) {
					MotherboardItem motherBoard = (MotherboardItem)stackSlot.getItem();
					if(motherBoard.getChip() != ((CpuItem)stack.getItem()).getChip()) {
						return false;
					}
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	

}
