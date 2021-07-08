package ru.mbutakov.auroracryptofarm.common;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	public void preInit() {

	}

	public void init() {
		CommonEvents ce = new CommonEvents();
		MinecraftForge.EVENT_BUS.register(ce);
		FMLCommonHandler.instance().bus().register(ce);
		
	}
	
	public void registerName(Item item,String nameItem) {
		
	}

}
