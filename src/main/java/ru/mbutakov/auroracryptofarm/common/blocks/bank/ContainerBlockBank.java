package ru.mbutakov.auroracryptofarm.common.blocks.bank;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;
import ru.mbutakov.auroracryptofarm.common.slots.SlotCard;
import ru.mbutakov.auroracryptofarm.common.slots.SlotCpu;
import ru.mbutakov.auroracryptofarm.common.slots.SlotFan;
import ru.mbutakov.auroracryptofarm.common.slots.SlotMotherboard;
import ru.mbutakov.auroracryptofarm.common.slots.SlotUsbFlash;
import ru.mbutakov.auroracryptofarm.common.slots.SlotUsbFlashBank;

@Getter
public class ContainerBlockBank extends Container  {

    private BlockBank block;
    private int slotID = 0;
    private EntityPlayer player;
    public InventoryBasic inv;
    
    public ContainerBlockBank( InventoryPlayer inventoryplayer,EntityPlayer player) {
        this.inv = new InentoryBank(player);
        this.player = player;
          addSlotToContainer(new SlotUsbFlashBank(inv, slotID++, 79, 58, this));
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryplayer, j + i * 9 + 9, 7 + j * 18, 114 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryplayer, i, 7 + i * 18, 172));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw)
    {
    	try {
    		 ItemStack itemstack = null;
    	        Slot slot = (Slot)this.inventorySlots.get(slotRaw);
    	        if (slot != null && slot.getHasStack())
    	        {
    	            ItemStack itemstack1 = slot.getStack();
    	            itemstack = itemstack1.copy();
    	            
				if (slotRaw <= 0) {
					if (!this.mergeItemStack(itemstack1, 7, this.inventorySlots.size(), true)) {
						return null;
					}
    	            }
    	            
    	            if (itemstack1.stackSize == 0)
    	            {
    	                slot.putStack((ItemStack)null);
    	            }
    	            else
    	            {
    	                slot.onSlotChanged();
    	            }

    	        }
    	        return null;
		} catch (Exception e) {
			// TODO: handle exception
		}


        return null;
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }
    
    public static void playSound() {
    	Minecraft.getMinecraft().thePlayer.playSound("cryptofarmaurora:s_bank", 0.1f, 1f);
    }
    
    public void onContainerClosed(EntityPlayer player) {
        if(inv.getStackInSlot(0) != null) {
        	if(inv.getStackInSlot(0) != null) {
        		  player.dropPlayerItemWithRandomChoice(inv.getStackInSlot(0), false);
        	}
        }

     }
    
    
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
