package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;
import ru.mbutakov.auroracryptofarm.common.items.GpuItem;

public class TileBlockPc extends TileEntity implements IInventory {

    private ItemStack[] items = new ItemStack[7];
    public boolean loaded;
    
    public int getSizeInventory()
    {
        return items.length;
    }
    
    public TileBlockPc() {
        this.loaded = false;
    }
    
    public void updateEntity() {
    	try {
    		  if(worldObj.getTotalWorldTime() % 20 == 1) {
    				super.updateEntity();
    				ItemStack stackCpu = getStackInSlot(0);
    				ItemStack stackVidiocard = getStackInSlot(1);
    				ItemStack stackMotherboard = getStackInSlot(2);
    				ItemStack stackUsbflash = getStackInSlot(6);
    				if(stackCpu != null && stackVidiocard != null && stackMotherboard != null && stackUsbflash != null) {
    					CpuItem cpu = (CpuItem) stackCpu.getItem();
    					double cpuX = cpu.getCofProcess(stackCpu);
    					double moneyAdd = 0;
    					if(getStackInSlot(5) != null) {
    						getStackInSlot(5).setItemDamage(getStackInSlot(5).getItemDamage() + 1);
    					}
    					if(getStackInSlot(3) != null) {
    						getStackInSlot(3).setItemDamage(getStackInSlot(3).getItemDamage() + 1);
    					}
    					if(getStackInSlot(4) != null) {
    						getStackInSlot(4).setItemDamage(getStackInSlot(4).getItemDamage() + 1);
    					}
    					stackVidiocard.setItemDamage(stackVidiocard.getItemDamage() + 1);
    					if(getStackInSlot(3) != null) {
    						moneyAdd += ((GpuItem)getStackInSlot(3).getItem()).getCoinAdd();
    					}
    					if(getStackInSlot(4) != null) {
    						moneyAdd += ((GpuItem)getStackInSlot(4).getItem()).getCoinAdd();
    					}
    					moneyAdd += ((GpuItem)stackVidiocard.getItem()).getCoinAdd();
    					UsbflashItem flash = (UsbflashItem) stackUsbflash.getItem();
    					flash.addCoin(stackUsbflash, moneyAdd * cpuX);
    				}else {
    					return;
    				}
    	        }
    			if(worldObj.getTotalWorldTime() % 100 == 1) {
    				ItemStack stackCpu = getStackInSlot(0);
    				ItemStack stackFan = getStackInSlot(5);
    				if(stackCpu != null) {
    					if(stackFan == null) {
    						stackCpu.setItemDamage(stackCpu.getItemDamage() + 5);
    					}else {
    						stackCpu.setItemDamage(stackCpu.getItemDamage() + 1);
    					}
    				}
    			}
		} catch (Exception e) {
			// TODO: handle exception
		}
        if (this.worldObj.isRemote) {
            return;
        }
      
		
    }
    
    public ItemStack getStackInSlot(int slot)
    {
        return items[slot];
    }

    public ItemStack decrStackSize(int slot, int amount)
    {
        if (items[slot] != null)
        {
            ItemStack itemstack;

            if (items[slot].stackSize == amount)
            {
                itemstack = items[slot];
                items[slot] = null;
                markDirty();
                return itemstack;
            }
            else
            {
                itemstack = items[slot].splitStack(amount);
                if (items[slot].stackSize == 0) items[slot] = null;
                markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (items[slot] != null)
        {
            ItemStack itemstack = items[slot];
            items[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        items[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }

        markDirty();
    }

    public String getInventoryName()
    {
    	
        return "container.storage";
    }

    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        items = new ItemStack[getSizeInventory()];
        for (int i = 0; i < list.tagCount(); ++i) {
        	NBTTagCompound comp = list.getCompoundTagAt(i); int j = comp.getByte("Slot") & 255; if (j >= 0 && j < items.length)
            {
                items[j] = ItemStack.loadItemStackFromNBT(comp);
            }
        }
    }
    

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < items.length; ++i)
        {
            if (items[i] != null)
            {
                NBTTagCompound comp = new NBTTagCompound();
                comp.setByte("Slot", (byte)i);
                items[i].writeToNBT(comp);
                list.appendTag(comp);
            }
        }
        nbt.setTag("Items", list);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {
    }

    public void closeInventory() {}

    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return true;
    }
	
    
    public Container getGuiContainer(InventoryPlayer inventory) {
        return (Container)new ContainerBlockPc(inventory, this);
      }

    
}
