package ru.mbutakov.auroracryptofarm.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.mbutakov.auroracryptofarm.client.render.RenderItemBlockPc;
import ru.mbutakov.auroracryptofarm.client.render.RenderTileEntityBlockPc;
import ru.mbutakov.auroracryptofarm.common.BlocksRegister;
import ru.mbutakov.auroracryptofarm.common.CommonProxy;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
	    ClientRegistry.bindTileEntitySpecialRenderer((Class)TileBlockPc.class, (TileEntitySpecialRenderer)new RenderTileEntityBlockPc());
	
	}
	
	public void init() {
		super.init();
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksRegister.BlockPc), new RenderItemBlockPc());

	}
	
}
