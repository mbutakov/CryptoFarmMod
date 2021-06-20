package ru.mbutakov.auroracryptofarm.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.utils.ModelWrapperDisplayList;

public class mbResourceLocation {

	   public static final IModelCustom blockPc = new ModelWrapperDisplayList((WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(Main.MODID, "models/blocks/pc.obj")));
	   public static final ResourceLocation blockPcTex = new ResourceLocation(Main.MODID, "textures/models/blocks/pcTex.png");
	   public static final IModelCustom gt710 = new ModelWrapperDisplayList((WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(Main.MODID, "models/blocks/gt710.obj")));
	   public static final ResourceLocation gt710tex = new ResourceLocation(Main.MODID, "textures/models/blocks/gt710.png");

}
