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
        if(bp.getTier() == EnumPcTier.MIDDLE) {
	        GL11.glPushMatrix();
	        RenderHelper.disableStandardItemLighting();
	        GL11.glTranslatef((float)x + 0.5f, (float)y + 0f, (float)z + 0.5f);
	        GL11.glColor4f(1f,1f,1.0f,1f);
	        GL11.glRotatef(90 - rotation, 0.0f, 1.0f, 0.0f);
	        GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
	        GL11.glScalef(0.1f, -0.1f, -0.1f);
	        this.bindTexture(mbResourceLocation.blockPcTex);
	        mbResourceLocation.blockPc.renderAll();
	        GL11.glPopMatrix();
	        if(tile.getStackInSlot(6) != null) {
	        	 GL11.glPushMatrix();
	            RenderHelper.disableStandardItemLighting();
	            this.bindTexture(mbResourceLocation.usbflash_tex);
	            GL11.glTranslatef((float)x + 0.46f, (float)y + 0.95f, (float)z + 0.64f);
	            GL11.glRotatef(180 - rotation, 0.0f, 1.0f, 0.0f);
	            GL11.glScalef(0.025f, 0.025f, 0.025f);
	            GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
	            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
	            mbResourceLocation.usbflash.renderAll();
	            GL11.glPopMatrix();
	        }
        }else if (bp.getTier() == EnumPcTier.LOW) {
        	GL11.glPushMatrix();
        	GL11.glDisable(GL11.GL_CULL_FACE);
 	        RenderHelper.disableStandardItemLighting();
 	        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.01f, (float)z + 0.5f);
 	        GL11.glColor4f(1f,1f,1.0f,1f);
 	        GL11.glRotatef(90 - rotation, 0.0f, 1.0f, 0.0f);
 	        GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
 	        GL11.glScalef(0.07f, -0.07f, -0.07f);
 	        this.bindTexture(mbResourceLocation.blockPcTexLow);
 	        mbResourceLocation.blockPc.renderAll();
 	        GL11.glPopMatrix();
        } else if(bp.getTier() == EnumPcTier.TOP) {
	        GL11.glPushMatrix();
	        RenderHelper.disableStandardItemLighting();
	        GL11.glTranslatef((float)x + 0.5f, (float)y + 0f, (float)z + 0.5f);
	        GL11.glColor4f(1f,1f,1.0f,1f);
	        GL11.glRotatef(90 - rotation, 0.0f, 1.0f, 0.0f);
	        GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
	        GL11.glScalef(0.12f, -0.12f, -0.12f);
	        this.bindTexture(mbResourceLocation.blockPcTexTop);
	        mbResourceLocation.blockPc.renderAll();
	        GL11.glPopMatrix();
        }
        
    }
    
    public void renderTileEntityAt(final TileEntity tile, final double x, final double y, final double z, final float f) {
        this.render((TileBlockPc)tile, x, y, z, f);
    }
}
