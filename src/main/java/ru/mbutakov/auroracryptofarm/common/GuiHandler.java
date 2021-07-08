package ru.mbutakov.auroracryptofarm.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.mbutakov.auroracryptofarm.client.gui.GuiBank;
import ru.mbutakov.auroracryptofarm.client.gui.GuiPc;
import ru.mbutakov.auroracryptofarm.common.blocks.bank.ContainerBlockBank;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        Block block = world.getBlock(x, y, z);
        if (te != null) {
            if (te instanceof TileBlockPc) {
                return ((TileBlockPc)te).getGuiContainer(player.inventory);
            }
        }
        if(ID == 2) {
        	return new ContainerBlockBank(player.inventory, player);
        }

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te != null) {
            if (te instanceof TileBlockPc) {
                return new GuiPc(player.inventory, (TileBlockPc)te);
            }
        }
        if(ID == 2) {
        	return new GuiBank(player.inventory);
        }
		return null;
	}

}
