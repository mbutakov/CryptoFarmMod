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
public class RenderItemUsbflash implements IItemRenderer {

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
		GL11.glScalef(0.12f, 0.12f, 0.12f);
		if(type == ItemRenderType.INVENTORY) {
			GL11.glScalef(1.5f, 1.5f, 1.5f);
			GL11.glTranslatef(3f, 2f, 3f);
		}
		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(1f,6f, 4f);
			GL11.glRotatef(220, 0, 1, 0);
		}
		if(type == ItemRenderType.EQUIPPED) {
			GL11.glScalef(1f, 1f, 1f);
			GL11.glTranslatef(5f, 2f, 5f);
			GL11.glRotatef(-40, 0, 1, 0);
		}
		if(type == ItemRenderType.ENTITY) {
			GL11.glScalef(0.7f, 0.7f, 0.7f);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(mbResourceLocation.usbflash_tex);
		mbResourceLocation.usbflash.renderAll();
		GL11.glPopMatrix();
	}
}
