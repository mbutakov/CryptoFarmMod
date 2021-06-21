package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.slots.SlotCard;
import ru.mbutakov.auroracryptofarm.common.slots.SlotCpu;
import ru.mbutakov.auroracryptofarm.common.slots.SlotFan;
import ru.mbutakov.auroracryptofarm.common.slots.SlotMotherboard;

@Getter
public class ContainerBlockPc extends Container  {

    protected TileBlockPc tile;
    private BlockPc block;
    private int slotID = 0;
    
    public ContainerBlockPc( InventoryPlayer inventoryplayer, TileBlockPc tile) {
        this.tile = tile;
        block = (BlockPc) tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
        addSlotToContainer(new SlotCpu(tile, slotID++, 20 + 1 * 18, 5 + 1 * 18, this));
        addSlotToContainer(new SlotCard(tile, slotID++, 20 + 1 * 18, 32 + 1 * 18, this,1));
        addSlotToContainer(new SlotMotherboard(tile, slotID++, 20 + 1 * 18, 56 + 1 * 18, this));
        addSlotToContainer(new SlotCard(tile, slotID++, 20 + 39, 32 + 1 * 18, this,2));
        addSlotToContainer(new SlotCard(tile, slotID++, 20 + 60, 32 + 1 * 18, this,3));
        addSlotToContainer(new SlotFan(tile, slotID++, 80 + 60, 5 + 1 * 18, this));
        //Инвентарь
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryplayer, j + i * 9 + 9, 7 + j * 18, 114 + i * 18));
            }
        }
        //Хотбар
        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryplayer, i, 7 + i * 18, 172));
        }
    }
        
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw)
    {
        return null;
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tile.isUseableByPlayer(player);
    }

}
