package ru.mbutakov.auroracryptofarm.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.mbutakov.auroracryptofarm.client.render.RenderItemBlockPc;
import ru.mbutakov.auroracryptofarm.client.render.RenderItemUsbflash;
import ru.mbutakov.auroracryptofarm.client.render.RenderItemVideocard;
import ru.mbutakov.auroracryptofarm.client.render.RenderTileEntityBlockPc;
import ru.mbutakov.auroracryptofarm.common.BlocksRegister;
import ru.mbutakov.auroracryptofarm.common.CommonProxy;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;
import ru.mbutakov.auroracryptofarm.utils.FontUtils;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
	    ClientRegistry.bindTileEntitySpecialRenderer((Class)TileBlockPc.class, (TileEntitySpecialRenderer)new RenderTileEntityBlockPc());
	}
	
	public void init() {
		super.init();
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPcLow), new RenderItemBlockPc(EnumPcTier.LOW));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPcMiddle), new RenderItemBlockPc(EnumPcTier.MIDDLE));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPcTop), new RenderItemBlockPc(EnumPcTier.TOP));
		MinecraftForgeClient.registerItemRenderer(ItemsRegister.item_card_100z, new RenderItemVideocard());
		MinecraftForgeClient.registerItemRenderer(ItemsRegister.item_usbflash, new RenderItemUsbflash());
		FontUtils.registerFont();

	}
	@Override
	public void registerName(Item item,String nameItem) {
		if(FMLCommonHandler.instance().getSide().isClient()) {
			LanguageRegistry.addName(item, nameItem);
		}
	}
	
}
