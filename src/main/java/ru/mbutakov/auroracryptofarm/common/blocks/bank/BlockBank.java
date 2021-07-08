package ru.mbutakov.auroracryptofarm.common.blocks.bank;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;

public class BlockBank extends Block {

	public BlockBank(String name, String nameTexture, CreativeTabs tab) {
		super(Material.wood);
		setCreativeTab(tab);
		setBlockName(name);
		setHardness(0.5f);
		setBlockTextureName(Main.MODID + ":blockBank");
		GameRegistry.registerBlock(this, name);
		Main.proxy.registerName(Item.getItemFromBlock(this), name);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
	       	 player.openGui(Main.instance, 2, world, x, y, z);
        }
        
		return true;
	}
	

}
