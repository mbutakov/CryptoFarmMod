package ru.mbutakov.auroracryptofarm.common.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.BlockPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.GpuItem;
import ru.mbutakov.auroracryptofarm.utils.EnumFormatMotherboard;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

public class SlotMotherboard extends Slot {

	private ContainerBlockPc container;
	
	public SlotMotherboard(IInventory inventory, int i, int x, int y, ContainerBlockPc s) {
		super(inventory, i, x, y);
		this.container = s;
	}

	@SuppressWarnings("unused")
	public boolean isItemValid(ItemStack stack) {
		ItemStack stackSlotCpu = container.getSlot(0).getStack();
		if(stackSlotCpu != null) {
			return false;
		}
		if(stack.getItem() instanceof MotherboardItem) {
		}else {
			return false;
		}
		EnumFormatMotherboard formatAccess[] = EnumPcTier.formatMotherboard(((BlockPc)container.getBlock()).getTier());
		EnumFormatMotherboard formatMotherboard = ((MotherboardItem)stack.getItem()).getFormat();
		boolean acces = false;
		for (int i = 0; i < formatAccess.length; i++) {
			if(formatAccess[i] == formatMotherboard) {
				acces = true;
			}
		}
		if(acces == false) {
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
