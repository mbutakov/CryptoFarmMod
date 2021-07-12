package ru.mbutakov.auroracryptofarm.common.blocks.bank;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;

public class BlockBank extends Block implements ITileEntityProvider {

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
	
	public void setBlockBoundsBasedOnState(IBlockAccess var1, int int2, int int3, int int4) {
			this.setBlockBounds(0F, 0.0F, 0.0F, 1F, 2.0F, 1F);
		
	}
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.floor_double((double)((double)(par5EntityLivingBase.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            par1World.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 1) {
            par1World.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
        if (l == 2) {
            par1World.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
        if (l == 3) {
            par1World.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

    }
	

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileBlockBank();
	}
	
    public int getRenderType() {
        return -1;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
	
    public boolean renderAsNormalBlock() {
        return false;
    }
	

}
