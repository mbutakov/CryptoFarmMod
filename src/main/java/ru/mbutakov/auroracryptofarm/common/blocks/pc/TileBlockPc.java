package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import ru.mbutakov.auroracryptofarm.client.EnergyTileLoadEvent;
import ru.mbutakov.auroracryptofarm.client.IEnergyTile;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.VideoCard;

public class TileBlockPc extends TileEntity implements IInventory, IEnergyTile {

	public double coin;
	
    private ItemStack[] items = new ItemStack[5];
    public boolean loaded;
    
    public int getSizeInventory()
    {
        return items.length;
    }
    
    public TileBlockPc() {
        this.coin = 0.0f;
        this.loaded = false;
    }
    
    public void updateEntity() {
        if (this.worldObj.isRemote) {
            return;
        }
        if(worldObj.getTotalWorldTime() % 20 == 1) {
			super.updateEntity();
			ItemStack stackCpu = getStackInSlot(0);
			ItemStack stackVidiocard = getStackInSlot(1);
			ItemStack stackMotherboard = getStackInSlot(2);
			if(stackCpu != null && stackVidiocard != null && stackMotherboard != null) {
				MotherboardItem motherboard = (MotherboardItem) stackMotherboard.getItem();
				CpuItem cpu = (CpuItem) stackCpu.getItem();
				double cpuX = cpu.getProcessorX();
				double moneyAdd = 0;
				if(getStackInSlot(3) != null) {
					getStackInSlot(3).setItemDamage(getStackInSlot(3).getItemDamage() + 1);
				}
				if(getStackInSlot(4) != null) {
					getStackInSlot(4).setItemDamage(getStackInSlot(4).getItemDamage() + 1);
				}
				stackVidiocard.setItemDamage(stackVidiocard.getItemDamage() + 1);
				if(getStackInSlot(3) != null) {
					moneyAdd += ((VideoCard)getStackInSlot(3).getItem()).getCoinAdd();
				}
				if(getStackInSlot(4) != null) {
					moneyAdd += ((VideoCard)getStackInSlot(3).getItem()).getCoinAdd();
				}
				moneyAdd += ((VideoCard)stackVidiocard.getItem()).getCoinAdd();
				addCoin(moneyAdd * cpuX);
				this.markDirty();
			}
           
        }
    }
    
    
    public void validate() {
        super.validate();
        this.onLoaded();
    }
    
    @Override
    public Packet getDescriptionPacket() {
 
       NBTTagCompound tagCompound = new NBTTagCompound();
 
       this.writeToNBT(tagCompound);//Можно записать всё, или отдельную информацию.
 
       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -1, tagCompound);
    }
   
    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound tagCompound = packet.func_148857_g();
        this.readFromNBT(tagCompound);
    }
    
    
    
    public void onLoaded() {
        if (!this.worldObj.isRemote) {
            MinecraftForge.EVENT_BUS.post((Event)new EnergyTileLoadEvent((IEnergyTile)this));
        }
        this.loaded = true;
    }
    
    public void invalidate() {
        if (this.loaded) {
            this.onUnloaded();
        }
        super.invalidate();
    }
    
    public void onUnloaded() {
        if (!this.worldObj.isRemote) {
          // MinecraftForge.EVENT_BUS.post((Event)new EnergyTileUnloadEvent((IEnergyTile)this));
        }
        this.loaded = false;
    }
   
    
    public double addCoin(final double amount) {
        this.coin += amount;
        this.markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return getCoin();
    }
    public double setCoin(final double amount) {
        this.coin = amount;
        this.markDirty();
        return getCoin();
    }
    public double getCoin() {
        return this.coin;
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
        this.coin = nbt.getDouble("coin");
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
        nbt.setDouble("coin", getCoin());
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
