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
public class RenderItemBlockPc implements IItemRenderer {

	private EnumPcTier tier;
	
	public RenderItemBlockPc(EnumPcTier tier) {
		this.tier = tier;
	}
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
			if (tier == EnumPcTier.TOP) {
				GL11.glScalef(0.8f, 0.8f, 0.8f);
			}else if(tier == EnumPcTier.MIDDLE) {
				GL11.glScalef(1.6f, 1.6f, 1.6f);
			}else {
				GL11.glScalef(2.5f, 2.5f, 2.5f);
			}
			if (type == ItemRenderType.INVENTORY) {
				if (tier == EnumPcTier.TOP) {
					GL11.glTranslatef(3f, 2.3f, 3f);
				}else if(tier == EnumPcTier.MIDDLE) {
					GL11.glTranslatef(3f, 2.15f, 3f);
				}else {
					GL11.glTranslatef(3f, 2.3f, 3f);
				}
			}
			if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				GL11.glTranslatef(0.3f, 0.3f, 0.3f);
			}
			if (type == ItemRenderType.EQUIPPED) {
				GL11.glScalef(2f, 2f, 2f);
				GL11.glTranslatef(1.5f, 0.7f, 2f);
				GL11.glRotatef(230, 0, 1, 0);
			}
			if (tier == EnumPcTier.TOP) {
				Minecraft.getMinecraft().renderEngine.bindTexture(mbResourceLocation.blockPcHighTex);
				mbResourceLocation.blockPcHigh.renderAll();
			}else if(tier == EnumPcTier.MIDDLE) {
				Minecraft.getMinecraft().renderEngine.bindTexture(mbResourceLocation.blockPcMiddleTex);
				mbResourceLocation.blockPcMiddle.renderAll();
			}else {
				Minecraft.getMinecraft().renderEngine.bindTexture(mbResourceLocation.blockPcLowTex);
				mbResourceLocation.blockPcLow.renderAll();
			}
			GL11.glPopMatrix();
	}
}
