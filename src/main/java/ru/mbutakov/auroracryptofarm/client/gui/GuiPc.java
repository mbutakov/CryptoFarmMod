package ru.mbutakov.auroracryptofarm.client.gui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Sound;

import com.ibm.icu.impl.duration.impl.Utils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;

public class GuiPc extends GuiContainer{
	
	private TileBlockPc tile;
	
	private static final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/gui/guiPc.png");
    public GuiPc(InventoryPlayer inventoryplayer, TileBlockPc tile) {
        super((Container)new ContainerBlockPc(inventoryplayer, tile));
        this.tile = tile;
        this.allowUserInput = false;
		ySize = 256;
    }

    public boolean hasCpu() {
		ItemStack itemStack = inventorySlots.getSlot(0).getStack();
    	if(itemStack != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean hasMotherboard() {
		ItemStack itemStack = inventorySlots.getSlot(2).getStack();
    	if(itemStack != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    public boolean hasItem(int slotId) {
		ItemStack itemStack = inventorySlots.getSlot(slotId).getStack();
    	if(itemStack != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
	
    @Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
    {
       	fontRendererObj.drawString("Монет " + ru.mbutakov.auroracryptofarm.utils.Utils.formatNumber(tile.coin), 8, 1 + 2, 0xff4d00);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
		mc.renderEngine.bindTexture(texture);
		int xOrigin = (width - xSize) / 2;
		int yOrigin = (height - ySize) / 2;
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xOrigin, yOrigin, 0, 0, xSize, ySize);
		if(hasMotherboard()) {
			drawTexturedModalRect(xOrigin + 16 + 1 * 21, yOrigin + 73 + 0 * 20, 193, 256, 18, 18);
		}else {
			drawTexturedModalRect(xOrigin + 16 + 1 * 21, yOrigin + 73 + 0 * 20, 175, 256, 18, 18);
		}
	
		if(inventorySlots.getSlot(2).getStack() != null) {
			MotherboardItem item = (MotherboardItem) inventorySlots.getSlot(2).getStack().getItem();
				if(hasItem(1)) {
					drawTexturedModalRect(xOrigin + 16 + 1 * 21, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
				}else {
					drawTexturedModalRect(xOrigin + 16 + 1 * 21, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);		
				}
			if(item.getCountVideocard() > 1) {
				if(hasItem(3)) {
					drawTexturedModalRect(xOrigin + 16 + 2 * 21, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
				}else {
					drawTexturedModalRect(xOrigin + 16 + 2 * 21, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);		
				}
			if(item.getCountVideocard() > 2)
				if(hasItem(4)) {
					drawTexturedModalRect(xOrigin + 16 + 3 * 21, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
				}else {
					drawTexturedModalRect(xOrigin + 16 + 3 * 21, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);		
				}
			}
		}
		if(inventorySlots.getSlot(2).getStack() != null) {
			if(hasCpu()) {
				drawTexturedModalRect(xOrigin + 16 + 1 * 21, yOrigin + 22 + 0 * 20, 193, 256, 18, 18);
			}else {	
				drawTexturedModalRect(xOrigin + 16 + 1 * 21, yOrigin + 22 + 0 * 20, 175, 256, 18, 18);
			}
		}

		
		GL11.glDisable(3042);
	}

}
