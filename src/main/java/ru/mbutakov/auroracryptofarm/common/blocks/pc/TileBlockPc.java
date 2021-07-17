package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.FanItem;
import ru.mbutakov.auroracryptofarm.common.items.GpuItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;
import ru.mbutakov.auroracryptofarm.utils.EnumFormatMotherboard;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

public class TileBlockPc extends TileEntity implements IInventory,ISidedInventory {

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
    		if(!worldObj.isRemote) {
    		  if(worldObj.getTotalWorldTime() % 20 == 1) {
    				super.updateEntity();
    				ItemStack stackCpu = getStackInSlot(0);
    				ItemStack stackVidiocard = getStackInSlot(1);
    				ItemStack stackVidiocard2 = getStackInSlot(3);
    				ItemStack stackVidiocard3 = getStackInSlot(4);
    				ItemStack stackMotherboard = getStackInSlot(2);
    				ItemStack stackUsbflash = getStackInSlot(6);
    				int countVideocard = 0;
    				if(stackMotherboard != null && stackMotherboard.getItem() != null) {
    					if(stackMotherboard.getItem() instanceof MotherboardItem) {
    						countVideocard = ((MotherboardItem)stackMotherboard.getItem()).getCountVideocard();
    					}else {
    						return;
    					}
    				}
    				if(stackVidiocard3 != null && stackMotherboard != null) {
    					if(countVideocard < 2) {
    						setInventorySlotContents(4, null);
    					}
    				}
    				if(stackVidiocard2 != null && stackMotherboard != null) {
    					if(countVideocard <= 2) {
    						setInventorySlotContents(3, null);
    					}
    				}
        			if(stackCpu != null) {
        				if(stackCpu.getItem() != null) {
        					if(stackCpu.getItem() instanceof CpuItem ) {
                			}else {
                				setInventorySlotContents(0, null);
                			}
        				}
        			}
        			ItemStack stackFan2 = getStackInSlot(5);
        			if(stackFan2 != null) {
        				if(stackFan2.getItem() != null) {
        					if(stackFan2.getItem() instanceof FanItem ) {
                			}else {
                				setInventorySlotContents(5, null);
                			}
        				}
        			}
        			ItemStack stackFlash2 = getStackInSlot(6);
        			if(stackFlash2 != null) {
        				if(stackFlash2.getItem() != null) {
        					if(stackFlash2.getItem() instanceof UsbflashItem ) {
                			}else {
                				setInventorySlotContents(6, null);
                			}
        				}
        			}
    				
    				if(stackCpu != null && ( stackVidiocard != null || stackVidiocard2 != null || stackVidiocard3 != null) && stackMotherboard != null && stackUsbflash != null) {
    					if(stackCpu.getItem() instanceof CpuItem) {
    					}else {
    						for(int i = 0; i < 3; i++) {
    							worldObj.spawnParticle("flame", xCoord + worldObj.rand.nextDouble(), yCoord + worldObj.rand.nextDouble(), zCoord + worldObj.rand.nextDouble(), 0, 0.1, 0);
    						}
    					}
      					if(stackMotherboard.getItem() instanceof MotherboardItem) {
    					}else {
    						for(int i = 0; i < 3; i++) {
    							worldObj.spawnParticle("flame", xCoord + worldObj.rand.nextDouble(), yCoord + worldObj.rand.nextDouble(), zCoord + worldObj.rand.nextDouble(), 0, 0.1, 0);
    						}
    					}
    					CpuItem cpu = (CpuItem) stackCpu.getItem();
    					double cpuX = cpu.getCofProcess(stackCpu);
    					double moneyAdd = 0;
    					if(getStackInSlot(5) != null) {
    						ItemStack stack = getStackInSlot(5);
    						if(stack.getItemDamage() < stack.getMaxDamage()) {
    							stack.setItemDamage(stack.getItemDamage() + 1);
    						}else {
    							setInventorySlotContents(5, null);
    						}
    					}
    					if(getStackInSlot(3) != null) {
     						ItemStack stack = getStackInSlot(3);
    						if(stack.getItemDamage() < stack.getMaxDamage()) {
    							stack.setItemDamage(stack.getItemDamage() + 1);
    						}else {
    							setInventorySlotContents(3, null);
    						}
    					}
    					if(getStackInSlot(4) != null) {
     						ItemStack stack = getStackInSlot(4);
    						if(stack.getItemDamage() < stack.getMaxDamage()) {
    							stack.setItemDamage(stack.getItemDamage() + 1);
    						}else {
    							setInventorySlotContents(4, null);
    						}
    					}
    					if(stackVidiocard != null) {
    						ItemStack stack = stackVidiocard;
    						if(stack.getItemDamage() < stack.getMaxDamage()) {
    							stack.setItemDamage(stack.getItemDamage() + 1);
    						}else {
    							setInventorySlotContents(1, null);
    						}
    					}
 
    					if(getStackInSlot(3) != null) {
    						moneyAdd += ((GpuItem)getStackInSlot(3).getItem()).getCoinAdd();
    					}
    					if(stackVidiocard3 != null) {
    						moneyAdd += ((GpuItem)stackVidiocard3.getItem()).getCoinAdd();
    					}
    					if(stackVidiocard != null) {
    						moneyAdd += ((GpuItem)stackVidiocard.getItem()).getCoinAdd();
    					}
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
    						if(stackCpu.getItemDamage() > stackCpu.getMaxDamage()) {
    							setInventorySlotContents(0, null);
    						}
    						stackCpu.setItemDamage(stackCpu.getItemDamage() + 5);
    					}else {
    						if(stackCpu.getItemDamage() > stackCpu.getMaxDamage()) {
    							setInventorySlotContents(0, null);
    						}
    						stackCpu.setItemDamage(stackCpu.getItemDamage() + 1);
    					}
    				}
    			}
    		}
		} catch (Exception e) {
			System.out.println(e);
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
    public boolean canInsertItem(final int slot, final ItemStack item, final int side) {
        return false;
    }
    
    public boolean canExtractItem(final int slot, final ItemStack item, final int side) {
        return false;
    }
    public Container getGuiContainer(InventoryPlayer inventory) {
        return (Container)new ContainerBlockPc(inventory, this);
      }

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[0];
	}

    
}
