package ru.mbutakov.auroracryptofarm.common.items;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;

public class FanItem extends Item{

	public FanItem(String name,String nameTexture,CreativeTabs tab,int maxDamage) {
		super();
		setCreativeTab(tab);
		setTextureName(Main.MODID + ":" + nameTexture);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		setMaxDamage(maxDamage);
		GameRegistry.registerItem(this,name);
		Main.proxy.registerName(this, name);
		setNoRepair();
	}
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
    	if(stack.getItemDamage() > 0) {
    		list.add(EnumChatFormatting.WHITE + "Осталось: " + (stack.getMaxDamage() - stack.getItemDamage()) + " секунд");
    	}
    }
	
}
