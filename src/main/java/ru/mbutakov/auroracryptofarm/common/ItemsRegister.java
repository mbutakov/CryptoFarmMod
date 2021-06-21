package ru.mbutakov.auroracryptofarm.common;

import net.minecraft.item.Item;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.FanItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.VideoCard;
import ru.mbutakov.auroracryptofarm.utils.EnumFormatMotherboard;

public class ItemsRegister {

	//видиокарты
	public static Item item_card_100z,item_card_150m,item_card_160mx,item_card_200g,item_card_1000x,item_card_titan;
	//процессор
	public static Item item_cpu_1gx,item_cpu_10z,item_cpu_titan;
	//материнка
	public static Item 
	item_motherboard_x100,
	item_motherboard_x200,
	item_motherboard_x300,
	item_motherboard_m100,
	item_motherboard_m200,
	item_motherboard_m300,
	item_motherboard_s100,
	item_motherboard_s200,
	item_motherboard_s300;
	//вентиляторы
	public static Item 
	item_fan_100,
	item_fan_500,
	item_fan_1000;
	
	
	public static void registerItems() {
		item_card_100z = new VideoCard("Aurora 100z", "card100z", Main.cryptoTab,0.1,86400);
		item_card_150m = new VideoCard("Aurora 150m", "card150m", Main.cryptoTab,0.12,86400);
		item_card_160mx = new VideoCard("Aurora 160mx", "card160mx", Main.cryptoTab,0.15,86400);
		item_card_200g = new VideoCard("Aurora 200g", "card200g", Main.cryptoTab,0.3,86400);
		item_card_1000x = new VideoCard("Aurora 1000x", "card1000x", Main.cryptoTab,0.5,86400);
		item_card_titan = new VideoCard("Aurora TITAN", "cardtitan", Main.cryptoTab,1,86400);
		item_cpu_1gx = new CpuItem("Aurora Core 1gx","core1gx",Main.cryptoTab,0.1f,250,1000);
		item_cpu_10z = new CpuItem("Aurora Core 10Z","core10z",Main.cryptoTab,0.5f,1500,1000);
		item_cpu_titan = new CpuItem("Aurora Core Titan","coretitan",Main.cryptoTab,1f,2000,1000);
		item_motherboard_x100 = new MotherboardItem("Aurora Motherboard x100","motherboardx299",Main.cryptoTab,2000,1,EnumFormatMotherboard.StandartATX);
		item_motherboard_x200 = new MotherboardItem("Aurora Motherboard x200","motherboardx299",Main.cryptoTab,2000,2,EnumFormatMotherboard.StandartATX);
		item_motherboard_x300 = new MotherboardItem("Aurora Motherboard x300","motherboardx299",Main.cryptoTab,2000,3,EnumFormatMotherboard.StandartATX);
		item_motherboard_m100 = new MotherboardItem("Aurora Motherboard m100","motherboardb460",Main.cryptoTab,1500,1,EnumFormatMotherboard.MiniITX);
		item_motherboard_m200 = new MotherboardItem("Aurora Motherboard m200","motherboardb460",Main.cryptoTab,1500,2,EnumFormatMotherboard.MiniITX);
		item_motherboard_m300 = new MotherboardItem("Aurora Motherboard m300","motherboardb460",Main.cryptoTab,1500,3,EnumFormatMotherboard.MiniITX);
		item_motherboard_s100 = new MotherboardItem("Aurora Motherboard s100","motherboardz10",Main.cryptoTab,300,1,EnumFormatMotherboard.MicroATX);
		item_motherboard_s200 = new MotherboardItem("Aurora Motherboard s200","motherboardz10",Main.cryptoTab,300,2,EnumFormatMotherboard.MicroATX);
		item_motherboard_s300 = new MotherboardItem("Aurora Motherboard s300","motherboardz10",Main.cryptoTab,300,3,EnumFormatMotherboard.MicroATX);
		item_fan_100 = new FanItem("Cooler 100", "fan100", Main.cryptoTab, 3600);
		item_fan_500 = new FanItem("Cooler 500", "fan500", Main.cryptoTab, 10800);
		item_fan_1000 = new FanItem("Cooler 1000", "fan1000", Main.cryptoTab, 86400);
	}
}
