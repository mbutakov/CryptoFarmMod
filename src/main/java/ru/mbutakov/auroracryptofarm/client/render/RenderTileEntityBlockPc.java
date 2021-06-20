package ru.mbutakov.auroracryptofarm.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import ru.mbutakov.auroracryptofarm.client.mbResourceLocation;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;

public class RenderTileEntityBlockPc extends TileEntitySpecialRenderer
{ 
    
    private void render(final TileBlockPc tile, final double x, final double y, final double z, final float f) {
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
        
        GL11.glPushMatrix();
        RenderHelper.disableStandardItemLighting();
        this.bindTexture(mbResourceLocation.gt710tex);
        GL11.glTranslatef((float)x + 0.65f, (float)y + 0.3f, (float)z + 0.13f);
        GL11.glRotatef(180 - rotation, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(0.025f, 0.025f, 0.025f);
        mbResourceLocation.gt710.renderAll();
        GL11.glPopMatrix();
    }
    
    public void renderTileEntityAt(final TileEntity tile, final double x, final double y, final double z, final float f) {
        this.render((TileBlockPc)tile, x, y, z, f);
    }
}
