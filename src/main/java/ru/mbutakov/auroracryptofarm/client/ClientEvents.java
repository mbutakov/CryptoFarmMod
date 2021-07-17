package ru.mbutakov.auroracryptofarm.client;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.client.gui.GuiBank;
import ru.mbutakov.auroracryptofarm.client.gui.GuiPc;
import ru.mbutakov.auroracryptofarm.utils.ShaderResourcePack;

public class ClientEvents {

	private static Field _listShaders;

	private static long start;

	private static int fadeTime = 600;

	public static int radius = 12;

	private static int colorFirst = 75000000;

	private static int colorSecond = 75000000;

	@Nonnull ShaderResourcePack dummyPack = new ShaderResourcePack();
	

	@SubscribeEvent
	public void onGuiChange(GuiOpenEvent event) throws JsonException {
			if (this._listShaders == null)
				this._listShaders = ReflectionHelper.findField(ShaderGroup.class,
						new String[] { "field_148031_d", "listShaders" });
			if ((Minecraft.getMinecraft()).theWorld != null && ShaderLinkHelper.getStaticShaderLinkHelper() != null) {
				EntityRenderer er = (Minecraft.getMinecraft()).entityRenderer;
				if (!er.isShaderActive() && event.gui != null && (event.gui instanceof GuiPc || event.gui instanceof GuiBank)) {
					Minecraft mc = Minecraft.getMinecraft();
					er.theShaderGroup = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(),
							mc.getFramebuffer(), new ResourceLocation("shaders/post/fade_in_blur.json"));
					er.updateShaderGroupSize(mc.displayWidth, mc.displayHeight);
					this.start = System.currentTimeMillis();
				} else if (er.isShaderActive() && event.gui == null) {
					er.deactivateShader();
				}
		}
	}

	private static float getProgress() {
		return Math.min((float) (System.currentTimeMillis() - start) / fadeTime, 1.0F);
	}
	  
	  @SubscribeEvent
	  public void onRenderTick(TickEvent.RenderTickEvent event) {
		  if(Minecraft.getMinecraft().currentScreen instanceof GuiPc || Minecraft.getMinecraft().currentScreen instanceof GuiBank){
			    if (event.phase == TickEvent.Phase.END && (Minecraft.getMinecraft()).currentScreen != null && (Minecraft.getMinecraft()).entityRenderer.isShaderActive()) {
				      ShaderGroup sg = (Minecraft.getMinecraft()).entityRenderer.getShaderGroup();
				      try {
				        List<Shader> shaders = (List<Shader>)this._listShaders.get(sg);
				        for (Shader s : shaders) {
				          ShaderUniform su = s.getShaderManager().func_147991_a("Progress");
				          if (su != null)
				            su.func_148090_a(getProgress()); 
				        } 
				      } catch (IllegalArgumentException|IllegalAccessException e) {
				        Throwables.propagate(e);
				      } 
				    }
		  }
	  }
	  
	  
	  public static int getBackgroundColor(boolean second) {
	    int color = second ? colorSecond : colorFirst;
	    int a = color >>> 24;
	    int r = color >> 16 & 0xFF;
	    int b = color >> 8 & 0xFF;
	    int g = color & 0xFF;
	    float prog = getProgress();
	    a = (int)(a * prog);
	    r = (int)(r * prog);
	    g = (int)(g * prog);
	    b = (int)(b * prog);
	    return a << 24 | r << 16 | b << 8 | g;
	  }

}
