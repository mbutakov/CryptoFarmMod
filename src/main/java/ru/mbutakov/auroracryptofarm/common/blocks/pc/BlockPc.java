package ru.mbutakov.auroracryptofarm.common.blocks.pc;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

@Getter
public class BlockPc extends BlockContainer implements ITileEntityProvider {

	private EnumPcTier tier;
	
	public BlockPc(String name, String nameTexture, CreativeTabs tab,EnumPcTier tier) {
		super(Material.ground);
		setCreativeTab(tab);
		setBlockName(name);
		setHardness(0.5f);
		setBlockTextureName(Main.MODID + ":blockPc");
		this.tier = tier;
		GameRegistry.registerBlock(this, name);
		Main.proxy.registerName(Item.getItemFromBlock(this), name);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileBlockPc();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block blockOld, int metadataOld) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileBlockPc) {
			TileBlockPc te = (TileBlockPc)tile;
			for (int i1 = 0; i1 < te.getSizeInventory(); ++i1) {
				ItemStack itemstack = te.getStackInSlot(i1);

				if (itemstack != null) {
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = world.rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}
		}

		super.breakBlock(world, x, y, z, blockOld, metadataOld);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int int2, int int3, int int4) {
		int angel = var1.getBlockMetadata(int2, int3, int4);
		float x = 0.31f;
		if (angel != 2 && angel != 0) {
			if(tier == EnumPcTier.TOP) {
				x = 0f;
				this.setBlockBounds(0F + x, 0.0F, 0.0F + x, 1F - x, 1.5F, 1F - x);
			}
			if(tier == EnumPcTier.LOW) {
				x = 0.34f;
				this.setBlockBounds(0F + x, 0.0F, 0.14F, 1F-x, 0.78F, 0.9F);
			}
			if(tier == EnumPcTier.MIDDLE) {
				 x = 0.31f;
				this.setBlockBounds(0F + x, 0.0F, 0.14F, 1F-x, 1F, 0.9F);
			}
		}else {
			if(tier == EnumPcTier.TOP) {
				x = 0f;
				this.setBlockBounds(0F + x, 0.0F, 0.0F + x, 1F - x, 1.5F, 1F - x);
			}
			if(tier == EnumPcTier.LOW) {
				x = 0.34f;
				this.setBlockBounds(0.1F, 0.0F, 0.0F + x, 0.87F, 0.78F, 1F - x);
			}
			if(tier == EnumPcTier.MIDDLE) {
				 x = 0.25f;
				this.setBlockBounds(0.116F, 0.0F, 0.1F + x, 0.96F, 1F, 0.9F - x);
			}
		}
		
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (world.getBlock(x, y - 1, z) instanceof BlockPc) {
			return false;
		}
		if (world.getBlock(x, y + 1, z) instanceof BlockPc) {
			return false;
		}
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z);
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	         if(!world.isRemote) {
	        	TileEntity te = world.getTileEntity(x, y, z);
	        	TileBlockPc te2 =  (TileBlockPc)te;
	        	 player.openGui(Main.instance, 1, world, x, y, z);
	         }
		return true;
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
