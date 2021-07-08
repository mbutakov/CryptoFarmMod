package ru.mbutakov.auroracryptofarm;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import ru.mbutakov.auroracryptofarm.common.BlocksRegister;
import ru.mbutakov.auroracryptofarm.common.CommonProxy;
import ru.mbutakov.auroracryptofarm.common.GuiHandler;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;

@Mod(name = Main.MODID, modid = Main.MODID, version = "1.0")

@Getter
public class Main {

	public static final CreativeTabs cryptoTab = new CreativeTabs("Crypto Tab") {
		@Override
		public Item getTabIconItem() {
			// TODO Auto-generated method stub
			return Items.apple;
		}
	};
	@SidedProxy(clientSide = "ru.mbutakov.auroracryptofarm.client.ClientProxy", serverSide = "ru.mbutakov.auroracryptofarm.common.CommonProxy")
	public static CommonProxy proxy;
	public static final String MODID = "cryptofarmaurora";
	public static File auroraDir;

	@Mod.Instance(MODID)
	public static Main instance;
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		auroraDir = new File(e.getModConfigurationDirectory().getParentFile(), "/" + Main.MODID + "/");
	      if(!auroraDir.exists()) {
	         auroraDir.mkdirs();
	         auroraDir.mkdir();
	      }
	      ItemsRegister.syncDir();
		      
		proxy.preInit();
		ItemsRegister.registerItems();
		BlocksRegister.registerBlocks();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerTileEntity(TileBlockPc.class, "TileBlockPc");

	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e) {
		proxy.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

	}

}
