package ru.mbutakov.auroracryptofarm.common.items;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;
import ru.mbutakov.auroracryptofarm.utils.Utils;

@Getter
@Setter
public class UsbflashItem extends Item {
	
	public UsbflashItem(String name,String nameTexture,CreativeTabs tab) {
		super();
		setCreativeTab(tab);
		setTextureName(Main.MODID + ":" + nameTexture);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		GameRegistry.registerItem(this,name);
		Main.proxy.registerName(this, name);
	}
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
    	   if (stack.hasTagCompound())
           {
    		   if(stack.getTagCompound().hasKey("coin")) {
    			   list.add(EnumChatFormatting.WHITE + "Храниться: " + Utils.formatNumber(stack.getTagCompound().getDouble("coin")));
    		   }
           }else {
        	   list.add(EnumChatFormatting.WHITE + "Не использовалась");
           }
    }
    
	public void addCoin(ItemStack stack, double coin) {
		if (hasNbtOrCreate(stack)) {
			if (stack.hasTagCompound()) {
				stack.getTagCompound().setDouble("coin", getCoin(stack) + coin);
			} else {

			}
		}
	}
    
    public double getCoin(ItemStack stack) {
    	   if (stack.hasTagCompound())
           {
    		   return stack.getTagCompound().getDouble("coin");
           }else {
        	   return 0;
           }
    }
    
    public boolean hasNbtOrCreate(ItemStack stack) {
 	   if (!stack.hasTagCompound())
       {
		   NBTTagCompound newNbt = new NBTTagCompound();
		   newNbt.setDouble("coin", 0);
		   stack.setTagCompound(newNbt);
		   return true;
       }
 	   return true;
    }
	
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
    	
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {}
    

}
