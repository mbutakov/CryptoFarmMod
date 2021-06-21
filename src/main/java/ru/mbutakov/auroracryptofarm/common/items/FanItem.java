package ru.mbutakov.auroracryptofarm.common.items;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.mbutakov.auroracryptofarm.Main;

public class FanItem extends Item{

	public FanItem(String name,String nameTexture,CreativeTabs tab,int maxDamage) {
		super();
		setCreativeTab(tab);
		setTextureName(Main.MODID + ":" + nameTexture);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		setMaxDamage(maxDamage);
		GameRegistry.registerItem(this,name);
		if(FMLClientHandler.instance().getSide().isClient()) {
			LanguageRegistry.addName(this, name);
		}
	}
	
}
