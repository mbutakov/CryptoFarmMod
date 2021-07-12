package ru.mbutakov.auroracryptofarm.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import ru.mbutakov.auroracryptofarm.client.mbResourceLocation;
import ru.mbutakov.auroracryptofarm.common.blocks.bank.BlockBank;
import ru.mbutakov.auroracryptofarm.common.blocks.bank.TileBlockBank;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.BlockPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

public class RenderTileEntityBlockBank extends TileEntitySpecialRenderer
{ 
    
    private void render(final TileBlockBank tile, final double x, final double y, final double z, final float f) {
    	Block blockPc = tile.getBlockType();
    	BlockBank bb = (BlockBank) blockPc;
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
	        GL11.glScalef(0.15f, -0.155f, -0.15f);
	        this.bindTexture(mbResourceLocation.blockBankTex);
	        mbResourceLocation.blockBank.renderAll();
	        GL11.glPopMatrix();
        
    }
    
    public void renderTileEntityAt(final TileEntity tile, final double x, final double y, final double z, final float f) {
        this.render((TileBlockBank)tile, x, y, z, f);
    }
}
