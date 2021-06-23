package ru.mbutakov.auroracryptofarm.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Sound;

import com.ibm.icu.impl.duration.impl.Utils;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;

public class GuiPc extends GuiContainer{
	
	private TileBlockPc tile;
    private double top;
    private double bottom;
    private double left;
    private double right;
	ArrayList<String> list = new ArrayList<String>();
    
	private static final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/gui/guiPc.png");
    public GuiPc(InventoryPlayer inventoryplayer, TileBlockPc tile) {
        super((Container)new ContainerBlockPc(inventoryplayer, tile));
        this.tile = tile;
        this.allowUserInput = false;
		ySize = 256;
		
    }

    @Override
    public void initGui()
    {
     super.initGui();
     this.top = this.height / 2.0;
     this.bottom = this.height - this.height / 3.0;
     this.left = this.width / 2.0;
     this.right = this.width - this.width / 2.0;
     this.buttonList.add(new GuiButton( 1, (int)right + 71, (int)top - 130, 16, 16, "?"));
     list.clear();
     list.add("1. Установите материнскую плату");
     list.add("2-3. Установите процессор,видеокарту ");
     list.add("4. Установите охлаждение (не обязательно)" );
     list.add("5. Установите флеш накопитель");
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
    	ItemStack usbflashstack = tile.getStackInSlot(6);
    	if(usbflashstack != null) {
    		UsbflashItem usbFlash = (UsbflashItem) usbflashstack.getItem();
        	fontRendererObj.drawString("Крипты на флешке " + ru.mbutakov.auroracryptofarm.utils.Utils.formatNumber(usbFlash.getCoin(usbflashstack)), 8, 1 + 2, 0xff4d00);
    	}else {
    		fontRendererObj.drawString("Флеш карта не вставлена", 8, 1 + 2, 0xff4d00);
    	}
    	fontRendererObj.drawString("Процессор", 4, 14, 0xffffff);
    	fontRendererObj.drawString("Видиокарта", 4, 40, 0xffffff);
     	fontRendererObj.drawString("Материнская плата", 4, 66, 0xffffff);
     	fontRendererObj.drawString("Охлаждение", 100, 14, 0xffffff);
     	fontRendererObj.drawString("Флешка", 106, 40, 0xffffff);
     	ItemStack sblockpc = new ItemStack(tile.getBlockType());
     	GL11.glPushMatrix();
     	GL11.glTranslatef(25, -44, 0);
      	GL11.glScalef(3f, 3f, 3f);
     	this.itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, sblockpc, 33, 33);
     	GL11.glPopMatrix();
 
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int x, int y) {
     	if(((GuiButton)buttonList.get(0)).func_146115_a()){
     		drawHoveringText(list,((GuiButton)buttonList.get(0)).xPosition + 10,((GuiButton)buttonList.get(0)).yPosition + 18, mc.fontRenderer);
     	}
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
		mc.renderEngine.bindTexture(texture);
		int xOrigin = (width - xSize) / 2;
		int yOrigin = (height - ySize) / 2;
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xOrigin, yOrigin, 0, 0, xSize-1, ySize);
		if(hasMotherboard()) {
			drawTexturedModalRect(xOrigin + 12, yOrigin + 76 + 0 * 20, 193, 256, 18, 18);
		}else {
			drawTexturedModalRect(xOrigin + 12, yOrigin + 76 + 0 * 20, 175, 256, 18, 18);
		}
		if(hasMotherboard())
		if(hasItem(6)) {
			drawTexturedModalRect(xOrigin + 88 + 1 * 21, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
		}else {
			drawTexturedModalRect(xOrigin + 88 + 1 * 21, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);
		}
		if(hasMotherboard())
		if(hasItem(5)) {
			drawTexturedModalRect(xOrigin + 88 + 1 * 21, yOrigin + 22 + 0 * 20, 193, 256, 18, 18);
		}else {
			drawTexturedModalRect(xOrigin + 88 + 1 * 21, yOrigin + 22 + 0 * 20, 175, 256, 18, 18);
		}
	
		if(inventorySlots.getSlot(2).getStack() != null) {
			MotherboardItem item = (MotherboardItem) inventorySlots.getSlot(2).getStack().getItem();
				if(hasItem(1)) {
					drawTexturedModalRect(xOrigin + 12, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
				}else {
					drawTexturedModalRect(xOrigin + 12, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);		
				}
			if(item.getCountVideocard() > 1) {
				if(hasItem(3)) {
					drawTexturedModalRect(xOrigin + 16 * 2 + 4, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
				}else {
					drawTexturedModalRect(xOrigin + 16 * 2 + 4, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);		
				}
			if(item.getCountVideocard() > 2)
				if(hasItem(4)) {
					drawTexturedModalRect(xOrigin + 16 * 3 + 12, yOrigin + 49 + 0 * 20, 193, 256, 18, 18);
				}else {
					drawTexturedModalRect(xOrigin + 16 * 3 + 12, yOrigin + 49 + 0 * 20, 175, 256, 18, 18);		
				}
			}
		}
		if(inventorySlots.getSlot(2).getStack() != null) {
			if(hasCpu()) {
				drawTexturedModalRect(xOrigin + 12, yOrigin + 23, 193, 256, 18, 18);
			}else {	
				drawTexturedModalRect(xOrigin + 12, yOrigin + 23, 175, 256, 18, 18);
			}
		}

		
		GL11.glDisable(3042);
	}
	
}
