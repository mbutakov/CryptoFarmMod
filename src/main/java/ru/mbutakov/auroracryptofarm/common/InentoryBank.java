package ru.mbutakov.auroracryptofarm.common;

import lombok.Getter;
import lombok.Setter;
import ml.luxinfine.economy.api.EconomyApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.blocks.bank.ContainerBlockBank;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;

public class InentoryBank extends InventoryBasic {
	@Getter
	@Setter
	public boolean transTVOYAMAMA = false;
	public EntityPlayer player;
	
	public InentoryBank(EntityPlayer player) {
		super("Aurora Bank", true, 1);
		this.player = player;
	}
	
	public void markDirty() {
		ItemStack stackFlash = this.getStackInSlot(0);
		if (stackFlash != null && stackFlash.getItem() instanceof UsbflashItem) {
			if (stackFlash.hasTagCompound() && stackFlash.getTagCompound().hasKey("coin")) {
				float addMoney = stackFlash.getTagCompound().getFloat("coin");
				stackFlash.setTagCompound(null);
				if(player instanceof EntityPlayerMP) {
					EconomyApi.addBalance(player.getPersistentID(), addMoney);
				}
					if(transTVOYAMAMA) {
						transTVOYAMAMA = !transTVOYAMAMA;
						ContainerBlockBank.playSound();
					}else {
						transTVOYAMAMA = !transTVOYAMAMA;
						ContainerBlockBank.playSound();
					}
			}
		}
		
	}
	

}
