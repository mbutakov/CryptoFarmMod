package ru.mbutakov.auroracryptofarm.client.render;

import org.lwjgl.opengl.GL11;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import ru.mbutakov.auroracryptofarm.client.mbResourceLocation;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

@Getter
public class RenderItemVideocard implements IItemRenderer {

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
			GL11.glScalef(0.9f, 0.9f, 0.9f);
			GL11.glTranslatef(2f, 1f, 3f);
		}
		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(0.3f,6f, 0.3f);
		}
		if(type == ItemRenderType.EQUIPPED) {
			GL11.glScalef(1f, 1f, 1f);
			GL11.glTranslatef(4f, 2f, 7f);
			GL11.glRotatef(230, 0, 1, 0);
		}
		if(type == ItemRenderType.ENTITY) {
			GL11.glScalef(0.7f, 0.7f, 0.7f);
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(mbResourceLocation.gt710tex);
		mbResourceLocation.gt710.renderAll();
		GL11.glPopMatrix();
	}
}
