package ru.mbutakov.auroracryptofarm.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.mbutakov.auroracryptofarm.client.gui.GuiPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te != null) {
            if (te instanceof TileBlockPc) {
                return ((TileBlockPc)te).getGuiContainer(player.inventory);
            }
        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te != null) {
            if (te instanceof TileBlockPc) {
                return new GuiPc(player.inventory, (TileBlockPc)te);
            }
        }
		return null;
	}

}
