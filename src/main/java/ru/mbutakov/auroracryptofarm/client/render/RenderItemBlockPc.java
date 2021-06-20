package ru.mbutakov.auroracryptofarm.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import ru.mbutakov.auroracryptofarm.client.mbResourceLocation;

public class RenderItemBlockPc implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO Auto-generated method stub
		GL11.glPushMatrix();
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		if(type == ItemRenderType.INVENTORY) {
			GL11.glTranslatef(3f, -2f, 3f);
		}
		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(0.3f, 0.3f, 0.3f);
		}
		if(type == ItemRenderType.EQUIPPED) {
			GL11.glScalef(2f, 2f, 2f);
			GL11.glTranslatef(1.5f, 0.7f, 2f);
			GL11.glRotatef(230, 0, 1, 0);
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(mbResourceLocation.blockPcTex);
		mbResourceLocation.blockPc.renderAll();
		GL11.glPopMatrix();
	}
}
