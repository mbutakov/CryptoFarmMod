package ru.mbutakov.auroracryptofarm.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import ru.mbutakov.auroracryptofarm.client.mbResourceLocation;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.BlockPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.FanItem;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

public class RenderTileEntityBlockPc extends TileEntitySpecialRenderer
{ 
    
    private void render(final TileBlockPc tile, final double x, final double y, final double z, final float f) {
    	Block blockPc = tile.getBlockType();
    	BlockPc bp = (BlockPc) blockPc;
    	int i = 0;
        int rotation = 0;
        switch (tile.getBlockMetadata() % 4) {
            case 0: {
                rotation = 0;
                break;
            }
            case 1: {
                rotation = 90;
                break;
            }
            case 2: {
                rotation = 180;
                break;
            }
            case 3: {
                rotation = 270;
            }
        }
        if(bp.getTier() == EnumPcTier.LOW) {
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)x + 0.5f, (float)y + 0f, (float)z + 0.5f);
	        GL11.glColor4f(1f,1f,1.0f,1f);
	        GL11.glRotatef(90 - rotation, 0.0f, 1.0f, 0.0f);
	        GL11.glScalef(2f, 2f, 2f);
	        this.bindTexture(mbResourceLocation.blockPcLowTex);
	        mbResourceLocation.blockPcLow.renderAll();
	        GL11.glPopMatrix();
        }
        if(bp.getTier() == EnumPcTier.MIDDLE) {
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)x + 0.5f, (float)y + 0f, (float)z + 0.5f);
	        GL11.glColor4f(1f,1f,1.0f,1f);
	        GL11.glRotatef(90 - rotation, 0.0f, 1.0f, 0.0f);
	        GL11.glScalef(1.4f, 1.4f, 1.4f);
	        this.bindTexture(mbResourceLocation.blockPcMiddleTex);
	        mbResourceLocation.blockPcMiddle.renderAll();
	        GL11.glPopMatrix();
	        if(tile.getStackInSlot(5) != null && tile.getStackInSlot(5).getItem() instanceof FanItem) {
		        GL11.glPushMatrix();
		        this.bindTexture(mbResourceLocation.blockPcLowTex);
		        float xx = 0.45f;
		        float zz = 0.144f;
		        if(rotation == 90) {
		        	 GL11.glTranslatef((float)x + xx, (float)y + 0.715f, (float)z + zz);
		        }
		        if(rotation == 180) {
		        	 GL11.glTranslatef((float)x + xx*2 - 0.05f, (float)y + 0.715f, (float)z + 0.45f);
		        }
		        if(rotation == 270) {
		        	 GL11.glTranslatef((float)x + 0.55F, (float)y + 0.715f, (float)z + 0.857f);
		        }
		        if(rotation == 0) {
		        	 GL11.glTranslatef((float)x + 0.145f, (float)y + 0.715f, (float)z + 0.54f);
		        }
		        GL11.glRotatef(0 - rotation, 0.0f, 1.0f, 0.0f);
		        GL11.glScalef(0.04f, 0.04f, 0.04f);
		        GL11.glRotatef(90 + (Minecraft.getMinecraft().thePlayer.ticksExisted*11), 1, 0, 0);
		        mbResourceLocation.blockPcHighFan.renderPart("ventilputor_Cylinder_Cylinder.001");
		        GL11.glPopMatrix();
	        }
        }
        if(bp.getTier() == EnumPcTier.TOP) {
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f);
	        GL11.glColor4f(1f,1f,1.0f,1f);
	        GL11.glRotatef(180 - rotation, 0.0f, 1.0f, 0.0f);
	        GL11.glScalef(1f, 1f, 1f);
	        this.bindTexture(mbResourceLocation.blockPcHighTex);
	        mbResourceLocation.blockPcHigh.renderAll();
	        GL11.glPopMatrix();
	        
        }
    }
    
    public void renderTileEntityAt(final TileEntity tile, final double x, final double y, final double z, final float f) {
        this.render((TileBlockPc)tile, x, y, z, f);
    }
}
