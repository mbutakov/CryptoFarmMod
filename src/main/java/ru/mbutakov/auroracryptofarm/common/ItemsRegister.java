package ru.mbutakov.auroracryptofarm.common;

import net.minecraft.item.Item;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.VideoCard;

public class ItemsRegister {

	//видиокарты
	public static Item item_card_100z,item_card_150m,item_card_160mx,item_card_200g,item_card_1000x,item_card_titan;
	//процессор
	public static Item item_cpu_1gx,item_cpu_10z,item_cpu_titan;
	//материнка
	public static Item item_motherboard_x299,item_motherboard_b460,item_motherboard_z10;
	
	
	public static void registerItems() {
		item_card_100z = new VideoCard("Aurora 100z", "card100z", Main.cryptoTab,0.1,86400);
		item_card_150m = new VideoCard("Aurora 150m", "card150m", Main.cryptoTab,0.12,86400);
		item_card_160mx = new VideoCard("Aurora 160mx", "card160mx", Main.cryptoTab,0.15,86400);
		item_card_200g = new VideoCard("Aurora 200g", "card200g", Main.cryptoTab,0.3,86400);
		item_card_1000x = new VideoCard("Aurora 1000x", "card1000x", Main.cryptoTab,0.5,86400);
		item_card_titan = new VideoCard("Aurora TITAN", "cardtitan", Main.cryptoTab,1,86400);
		item_cpu_1gx = new CpuItem("Aurora Core 1gx","core1gx",Main.cryptoTab,0.1f,1240,300);
		item_cpu_10z = new CpuItem("Aurora Core 10Z","core10z",Main.cryptoTab,0.5f,1500,300);
		item_cpu_titan = new CpuItem("Aurora Core Titan","coretitan",Main.cryptoTab,1f,2000,300);
		item_motherboard_x299 = new MotherboardItem("Aurora Motherboard x299","motherboardx299",Main.cryptoTab,2000,3);
		item_motherboard_b460 = new MotherboardItem("Aurora Motherboard b460","motherboardb460",Main.cryptoTab,1500,2);
		item_motherboard_z10 = new MotherboardItem("Aurora Motherboard z10","motherboardz10",Main.cryptoTab,1240,1);
		
	}
}
