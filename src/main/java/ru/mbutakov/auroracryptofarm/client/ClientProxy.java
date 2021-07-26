package ru.mbutakov.auroracryptofarm.client;

import java.util.List;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import ru.mbutakov.auroracryptofarm.client.config.CryptConfig;
import ru.mbutakov.auroracryptofarm.client.render.RenderItemBlockPc;
import ru.mbutakov.auroracryptofarm.client.render.RenderItemUsbflash;
import ru.mbutakov.auroracryptofarm.client.render.RenderTileEntityBlockBank;
import ru.mbutakov.auroracryptofarm.client.render.RenderTileEntityBlockPc;
import ru.mbutakov.auroracryptofarm.common.BlocksRegister;
import ru.mbutakov.auroracryptofarm.common.CommonProxy;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;
import ru.mbutakov.auroracryptofarm.common.blocks.bank.TileBlockBank;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;
import ru.mbutakov.auroracryptofarm.utils.FontUtils;
import ru.mbutakov.auroracryptofarm.utils.ShaderResourcePack;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
		CryptConfig.load();
	    ClientRegistry.bindTileEntitySpecialRenderer((Class)TileBlockPc.class, (TileEntitySpecialRenderer)new RenderTileEntityBlockPc());
	    ClientRegistry.bindTileEntitySpecialRenderer((Class)TileBlockBank.class, (TileEntitySpecialRenderer)new RenderTileEntityBlockBank());
		
	}
	
	public void init() {
		super.init();
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPcLow), new RenderItemBlockPc(EnumPcTier.LOW));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPcMiddle), new RenderItemBlockPc(EnumPcTier.MIDDLE));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPcTop), new RenderItemBlockPc(EnumPcTier.TOP));
		MinecraftForgeClient.registerItemRenderer(ItemsRegister.item_usbflash, new RenderItemUsbflash());
		FontUtils.registerFont();
		
		final ClientEvents cevents = new ClientEvents();
		MinecraftForge.EVENT_BUS.register(cevents);
		FMLCommonHandler.instance().bus().register(cevents);
	    ((List<ShaderResourcePack>)ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), new String[] { "field_110449_ao", "defaultResourcePacks" })).add(cevents.dummyPack);
	    ((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener((IResourceManagerReloadListener)cevents.dummyPack);
	
		 

	}
	@Override
	public void registerName(Item item,String nameItem) {
		if(FMLCommonHandler.instance().getSide().isClient()) {
			LanguageRegistry.addName(item, nameItem);
		}
	}
	
}
