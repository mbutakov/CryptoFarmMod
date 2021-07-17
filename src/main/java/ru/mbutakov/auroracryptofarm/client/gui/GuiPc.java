package ru.mbutakov.auroracryptofarm.client.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.client.ClientProxy;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.ContainerBlockPc;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.TileBlockPc;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;
import ru.mbutakov.auroracryptofarm.common.slots.SlotCard;
import ru.mbutakov.auroracryptofarm.common.slots.SlotCpu;
import ru.mbutakov.auroracryptofarm.common.slots.SlotFan;
import ru.mbutakov.auroracryptofarm.common.slots.SlotMotherboard;
import ru.mbutakov.auroracryptofarm.common.slots.SlotUsbFlash;
import ru.mbutakov.auroracryptofarm.utils.DrawHelper;
import ru.mbutakov.auroracryptofarm.utils.FontUtils;
import ru.mbutakov.auroracryptofarm.utils.FontUtils.FontContainer;
import ru.mbutakov.auroracryptofarm.utils.Utils;

public class GuiPc extends GuiScreen{
	
	private TileBlockPc tile;
    protected int xSize = 5555;
    /** The Y size of the inventory window in pixels. */
    protected int ySize = 166;
    private double top;
    private double bottom;
    private double left;
    private double right;
	ArrayList<String> list = new ArrayList<String>();
    protected float minAspect;
    private InventoryPlayer invPlayer;
    private static final ResourceLocation slotHasStack = new ResourceLocation(Main.MODID, "textures/gui/slotHasStack.png");
    private static final ResourceLocation slotBlocked = new ResourceLocation(Main.MODID, "textures/gui/slotBlocked.png");
    private static final ResourceLocation slotAllowed = new ResourceLocation(Main.MODID, "textures/gui/slotAllowed.png");
	private static final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/gui/ui.png");
	private static final ResourceLocation overlay = new ResourceLocation(Main.MODID, "textures/gui/overlay.png");
	private static final ResourceLocation blockedLay = new ResourceLocation(Main.MODID, "textures/gui/blockedLay.png");
	private static final ResourceLocation blockedLayR = new ResourceLocation(Main.MODID, "textures/gui/blockedLayR.png");
	private static final ResourceLocation blockedRLay = new ResourceLocation(Main.MODID, "textures/gui/blockedRLay.png");
	private static final ResourceLocation blockedRLayR = new ResourceLocation(Main.MODID, "textures/gui/blockedRLayR.png");
	
	private boolean isFinished;
    public Container inventorySlots;
    protected int guiLeft;
    /** Starting Y position for the Gui. Inconsistent use for Gui backgrounds. */
    protected int guiTop;
    private Slot theSlot;
    /** Used when touchscreen is enabled. */
    private Slot clickedSlot;
    /** Used when touchscreen is enabled. */
    private boolean isRightMouseClick;
    /** Used when touchscreen is enabled */
    private ItemStack draggedStack;
    private int field_147011_y;
    private int field_147010_z;
    private Slot returningStackDestSlot;
    private long returningStackTime;
    /** Used when touchscreen is enabled */
    private ItemStack returningStack;
    private Slot field_146985_D;
    private long field_146986_E;
    protected final Set field_147008_s = new HashSet();
    protected boolean field_147007_t;
    private int field_146987_F;
    private int field_146988_G;
    private boolean field_146995_H;
    private int field_146996_I;
    private long field_146997_J;
    private Slot field_146998_K;
    private int field_146992_L;
    private boolean field_146993_M;
    private ItemStack field_146994_N;
    
    public GuiPc(InventoryPlayer inventoryplayer, TileBlockPc tile) {
        this.inventorySlots = (Container)new ContainerBlockPc(inventoryplayer, tile);
        this.tile = tile;
        this.allowUserInput = false;
        this.mc = Minecraft.getMinecraft();
        this.invPlayer = inventoryplayer;
        this.isFinished = false;
        this.mc.thePlayer.openContainer = this.inventorySlots;
		
    }

    @Override
	public void initGui() {
     super.initGui();
     this.top = 0;
     this.bottom = this.height;
     this.left = 0;
     this.right = this.width;
     xSize = getRightElementPosX(670);
     ySize = getRightElementPosY(640);
     guiLeft = getRightElementPosX(470);
     guiTop = getRightElementPosY(200);
    }
    
    @Override
    public void drawDefaultBackground()
    {
	    GL11.glPushMatrix();
	    GL11.glEnable(GL11.GL_BLEND);
	    GL11.glColor4f(1f, 1f, 1f, 0.8f);
		this.mc.getTextureManager().bindTexture(overlay);
		DrawHelper.drawCustom((int) this.left, (int) (this.right - this.left), (int) (this.bottom - this.top),
				(int) this.top);
		GL11.glPopMatrix();
    }
    
    @Override
    public void drawWorldBackground(int p_146270_1_)
    {
        if (this.mc.theWorld != null)
        {
            this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        }
        else
        {
            this.drawBackground(p_146270_1_);
        }
    }
    
    
  
    
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		drawDefaultBackground();
		GL11.glDisable(GL11.GL_LIGHTING);
		drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
		GL11.glEnable(GL11.GL_LIGHTING);

		
		
		 this.theSlot = null;
	       int k = this.guiLeft;
	        int l = this.guiTop;
		  InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
		  ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;
		   short short1 = 240;
	        short short2 = 240;
	     
		  if(itemstack != null) {
		  }
		     int k1;
		     
		        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
		        {
		            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
		            this.func_146977_a(slot);
		            int j1 = slot.xDisplayPosition;
		            k1 = slot.yDisplayPosition;
	                int posX = getRightElementPosX(j1 * 3.82f) + getRightElementPosX(469);
	                int size = getRightElementPosX(62f);
	                int sizeY = getRightElementPosY(62F);
	                int posY = getRightElementPosY(k1 * 3.95f) + getRightElementPosY(71);
		            if (this.isMouseOverSlot(slot, p_73863_1_, p_73863_2_) && slot.func_111238_b())
		            {
		                this.theSlot = slot;
		                GL11.glDisable(GL11.GL_LIGHTING);
		                GL11.glDisable(GL11.GL_DEPTH_TEST);
		                GL11.glColorMask(true, true, true, false);
		                this.drawGradientRect(posX, posY,posX + size, posY +sizeY, -2130706433, -2130706433);
		                GL11.glColorMask(true, true, true, true);
		                GL11.glEnable(GL11.GL_LIGHTING);
		                GL11.glEnable(GL11.GL_DEPTH_TEST);
		            }
		        }
		     
	        if (itemstack != null)
	        {
	            byte b0 = 8;
	            k1 = this.draggedStack == null ? 8 : 16;
	            String s = null;

	            if (this.draggedStack != null && this.isRightMouseClick)
	            {
	                itemstack = itemstack.copy();
	                itemstack.stackSize = MathHelper.ceiling_float_int((float)itemstack.stackSize / 2.0F);
	            }
	            else if (this.field_147007_t && this.field_147008_s.size() > 1)
	            {
	                itemstack = itemstack.copy();
	                itemstack.stackSize = this.field_146996_I;

	                if (itemstack.stackSize == 0)
	                {
	                    s = "" + EnumChatFormatting.YELLOW + "0";
	                }
	            }

	           drawItemStack(p_73863_1_- getRightElementPosX(20),p_73863_2_ - getRightElementPosY(20), itemstack);
	        }
	        
	        if (this.returningStack != null)
	        {
	            float f1 = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;

	            if (f1 >= 1.0F)
	            {
	                f1 = 1.0F;
	                this.returningStack = null;
	            }

	            k1 = this.returningStackDestSlot.xDisplayPosition - this.field_147011_y;
	            int j2 = this.returningStackDestSlot.yDisplayPosition - this.field_147010_z;
	            int l1 = this.field_147011_y + (int)((float)k1 * f1);
	            int i2 = this.field_147010_z + (int)((float)j2 * f1);
	            drawItemStack(i2, l1, this.returningStack);
	        }
	        
	        if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack())
	        {
	            ItemStack itemstack1 = this.theSlot.getStack();
	            this.renderToolTip(itemstack1, p_73863_1_, p_73863_2_);
	        }
    }
	
	@Override
    protected void renderToolTip(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_)
    {
        List list = p_146285_1_.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

        for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, p_146285_1_.getRarity().rarityColor + (String)list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
            }
        }

        FontRenderer font = p_146285_1_.getItem().getFontRenderer(p_146285_1_);
        drawHoveringText(list, p_146285_2_, p_146285_3_, (font == null ? fontRendererObj : font));
    }
	
	@Override
    protected void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font)
    {
        if (!p_146283_1_.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = p_146283_1_.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int j2 = p_146283_2_ + 12;
            int k2 = p_146283_3_ - 12;
            int i1 = 8;

            if (p_146283_1_.size() > 1)
            {
                i1 += 2 + (p_146283_1_.size() - 1) * 10;
            }

            if (j2 + k > this.width)
            {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > this.height)
            {
                k2 = this.height - i1 - 6;
            }

            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            int j1 = -267386865;
            this.drawGradientRect(j2 - 4, k2 - 4, j2 + k + 4, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 + i1 + 3, j2 + k + 4, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = -799999999;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
            {
                String s1 = (String)p_146283_1_.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }


            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
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
    private int getRightElementPosX(double x) {
        return (int)(this.left + (this.right - this.left) / 1600.0D * x);
      }
      
      private int getRightElementPosY(double y) {
        return (int)(this.top + (this.bottom - this.top) / 900.0D * y);
      }

    public void drawSlot(double xPos,double yPos,double sizeX,double sizeY,double startXWidth,double startYWidth, double xWidth,double yWidth,Slot slot) {
    	int []array = Utils.getNextDetail(inventorySlots);
    	if(slot.getSlotIndex() == 0) {
    		if(array[1] == 0) {
    		    this.mc.getTextureManager().bindTexture(slotBlocked);
        	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
    		}else {
            	if(slot.getHasStack()) {
            	    this.mc.getTextureManager().bindTexture(slotHasStack);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}else {
            	    this.mc.getTextureManager().bindTexture(slotAllowed);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}
    		}
    	}else if(slot.getSlotIndex() == 1) {
    		if(array[2] < 1) {
    		    this.mc.getTextureManager().bindTexture(slotBlocked);
        	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
    		}else {
            	if(slot.getHasStack()) {
            	    this.mc.getTextureManager().bindTexture(slotHasStack);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}else {
            	    this.mc.getTextureManager().bindTexture(slotAllowed);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}
    		}
    	}else if(slot.getSlotIndex() == 3) {
    		if(array[2] < 2) {
    		    this.mc.getTextureManager().bindTexture(slotBlocked);
        	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
    		}else {
            	if(slot.getHasStack()) {
            	    this.mc.getTextureManager().bindTexture(slotHasStack);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}else {
            	    this.mc.getTextureManager().bindTexture(slotAllowed);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}
    		}	
    	}else if(slot.getSlotIndex() == 4) {
    		if(array[2] < 3) {
    		    this.mc.getTextureManager().bindTexture(slotBlocked);
        	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
    		}else {
            	if(slot.getHasStack()) {
            	    this.mc.getTextureManager().bindTexture(slotHasStack);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}else {
            	    this.mc.getTextureManager().bindTexture(slotAllowed);
            	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
            	}
    		}	
    		
    	}else {
        	if(slot.getHasStack()) {
        	    this.mc.getTextureManager().bindTexture(slotHasStack);
        	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
        	}else {
        	    this.mc.getTextureManager().bindTexture(slotAllowed);
        	    DrawHelper.drawTexturedModalRect(xPos, sizeX, sizeY, yPos, startXWidth, startYWidth, xWidth, yWidth);
        	}
    	}

    }
    
    public void drawSlotLay(double xPos,double yPos,double sizeX,double sizeY,double startXWidth,double startYWidth, double xWidth,double yWidth,boolean blocked,float rotate,String text) {
    	GL11.glPushMatrix();    
    	int color;
  		if(blocked) {
			color = 0xEB5757;
		}else {
			color = 0x2F80ED;
		}
    	if(rotate == 180) {
    		if(blocked) {
    			this.mc.getTextureManager().bindTexture(blockedRLayR);
    		}else {
    			this.mc.getTextureManager().bindTexture(blockedLayR);
    		}
        	 	DrawHelper.drawTexturedModalRect(getRightElementPosX(xPos), getRightElementPosX(sizeX), getRightElementPosY(sizeY), getRightElementPosY(yPos), startXWidth, startYWidth, xWidth, yWidth);
        	 	drawCustomString(text, xPos + 10, yPos + 18, FontUtils.FontMonsterrat, 11.8f,color);
    	}else {
    		if(blocked) {
    			this.mc.getTextureManager().bindTexture(blockedRLay);
    		}else {
    			this.mc.getTextureManager().bindTexture(blockedLay);
    		}
        	 	DrawHelper.drawTexturedModalRect(getRightElementPosX(xPos), getRightElementPosX(sizeX), getRightElementPosY(sizeY), getRightElementPosY(yPos), startXWidth, startYWidth, xWidth, yWidth);
        		drawCustomString(text, xPos + 10, yPos + 18, FontUtils.FontMonsterrat, 11.8f,color);
    	}
    	GL11.glPopMatrix();
    }
    	    
    public void drawCustomString(String text,double posX,double posY,FontContainer font,float scale,int color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(getRightElementPosX(posX), getRightElementPosY(posY), 0.0F);
		scale = getRightElementPosX(scale);
		GL11.glScaled(0.0625D * Math.abs(scale), 0.0625D * Math.abs(scale), 1.0D);
        font.drawString(text, 0, 0, color);
        GL11.glPopMatrix();
    }
    
    public void finalDrawSlotLay() {
    	int []array = Utils.getNextDetail(inventorySlots);
    	String string = " ";
    	if(!inventorySlots.getSlot(5).getHasStack()) {
    		string = "Вы можете установить охлаждение";
    		drawSlotLay(240, 335, 235, 30, 0, 0, 256, 256, false,180,string);
    	}
       	if(!inventorySlots.getSlot(6).getHasStack()) {
    		string = "Вы можете установить память";
    		drawSlotLay(265, 425, 210, 30, 0, 0, 256, 256, false,180,string);
    	}

		
    	if(array[1] == 1) {
    		string = "Вы можете установить процессор";
    		drawSlotLay(250, 250, 225, 30, 0, 0, 256, 256, false,180,string);
    	}else if(array[1] == 0) {
    		string = "Вы не можете установить процессор";
    		drawSlotLay(230, 250, 245, 30, 0, 0, 256, 256, true,180,string);
    	}
    	if(array[2] < 1) {
    		string = "Вы не можете установить видеокарту";
    		drawSlotLay(1120, 250, 245, 30, 0, 0, 256, 256, true,0,string);
    	}
    	if(array[2] < 2) {
      		string = "Вы не можете установить видеокарту";
    		drawSlotLay(1120, 335, 245, 30, 0, 0, 256, 256, true,0,string);
    	}
    	if(array[2] < 3) {
      		string = "Вы не можете установить видеокарту";
    		drawSlotLay(1120, 425, 245, 30, 0, 0, 256, 256, true,0,string);
    	}
    	int yOf = 0;
    	for(int i = 0; i < array[2]; i++) {
    		yOf += 87;
     		string = "Вы можете установить видеокарту";
     		if(i == 0)
     		if(!inventorySlots.getSlot(1).getHasStack()) {
     			drawSlotLay(1120, 250, 228, 30, 0, 0, 256, 256, false,0,string);
     		}
     		if(i == 1)
     		if(!inventorySlots.getSlot(3).getHasStack()) {
     			drawSlotLay(1120, 335, 228, 30, 0, 0, 256, 256, false,0,string);
     		}
    		if(i == 2)
         		if(!inventorySlots.getSlot(4).getHasStack()) {
         			drawSlotLay(1120, 425, 228, 30, 0, 0, 256, 256, false,0,string);
         	}
    	}
    		
    	
    }
    
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int x, int y) {

		
		GL11.glPushMatrix();
		GL11.glAlphaFunc(GL11.GL_GREATER, 0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1, 1, 1, 1f);
	    drawSlot(getRightElementPosX(495.0D),getRightElementPosY(232.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(0));
	    drawSlot(getRightElementPosX(495.0D),getRightElementPosY(319.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(5));
	    drawSlot(getRightElementPosX(495.0D),getRightElementPosY(411.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(6));
	    drawSlot(getRightElementPosX(1046.0D),getRightElementPosY(232.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(1));
	    drawSlot(getRightElementPosX(1046.0D),getRightElementPosY(319.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(3));
	    drawSlot(getRightElementPosX(1046.0D),getRightElementPosY(411.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(4));
	    drawSlot(getRightElementPosX(771.0D),getRightElementPosY(411.0D), getRightElementPosX(62), getRightElementPosY(62), 0.0D, 0.0D, 256.0D, 256.0D,inventorySlots.getSlot(2));
	    drawCustomString("Память", 500, 487, FontUtils.FontMonsterrat, 13,0x697C69);
	    drawCustomString("Охлаждение", 490, 399, FontUtils.FontMonsterrat, 13,0x697C69);
	    drawCustomString("Процессор", 490, 309, FontUtils.FontMonsterrat, 13,0x697C69);
	    drawCustomString("Материнская плата", 740, 487, FontUtils.FontMonsterrat, 13,0x697C69);
	    drawCustomString("Видеокарта 1", 1035, 309, FontUtils.FontMonsterrat, 13,0x697C69);
	    drawCustomString("Видеокарта 2", 1035, 399, FontUtils.FontMonsterrat, 13,0x697C69);
	    drawCustomString("Видеокарта 3", 1035, 487, FontUtils.FontMonsterrat, 13,0x697C69);
	    finalDrawSlotLay();
	    try {
		    if(hasItem(6)) {
		    	UsbflashItem usbFlash = (UsbflashItem) inventorySlots.getSlot(6).getStack().getItem();
		    	String text = "У вас: " + Utils.formatNumber(usbFlash.getCoin(inventorySlots.getSlot(6).getStack())) + " ACOIN";
		     	drawCustomString(text, 790 - (5 *text.length()), 130, FontUtils.FontMonsterratSemi, 20,0x9FC69F);
		    }else {
		    	drawCustomString("Флеш карта не вставлена", 660, 130, FontUtils.FontMonsterratSemi, 20,0x9FC69F);
		    } 
		} catch (Exception e) {
			// TODO: handle exception
		}
	    GL11.glPushMatrix();
		double a = getRightElementPosX(-20) / 1;
		double b = getRightElementPosY(-20) / 1;
	    GL11.glColor3f(1f, 1f, 1f);
	    GL11.glPopMatrix();
		this.mc.getTextureManager().bindTexture(texture);
		DrawHelper.drawCustom((int) this.left, (int) (this.right - this.left), (int) (this.bottom - this.top),
				(int) this.top);
		GL11.glPopMatrix();
		    
		
	}

	private void drawItemStack(int x, int y, ItemStack itemStack) {
		if(itemStack == null)
			return;
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		double a = (this.right - this.left) / 1600.0D * 48.0D;
		double b = (this.bottom - this.top) / 900.0D * 48.0D;
		GL11.glTranslatef(x, y, 0.0F);
		GL11.glScaled(0.0625D * a, 0.0625D * b, 1.0D);
		this.zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
		itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemStack, 0, 0);
		itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemStack, 0, 0,itemStack.stackSize > 1 ? itemStack.stackSize + "" : "");
		
		itemRender.zLevel = 0.0F;
		GL11.glPopMatrix();
	}

	public boolean doesGuiPauseGame() {
		return false;
	}
	
	
	   protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
	    {
	        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	        boolean flag = p_73864_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
	        Slot slot = this.getSlotAtPosition(p_73864_1_, p_73864_2_);
	        long l = Minecraft.getSystemTime();
	        this.field_146993_M = this.field_146998_K == slot && l - this.field_146997_J < 250L && this.field_146992_L == p_73864_3_;
	        this.field_146995_H = false;
	        if (p_73864_3_ == 0 || p_73864_3_ == 1 || flag)
	        {
	            int i1 = this.guiLeft;
	            int j1 = this.guiTop;
	            boolean flag1 = p_73864_1_ < i1 || p_73864_2_ < j1 || p_73864_1_ >= i1 + this.xSize || p_73864_2_ >= j1 + this.ySize;
	            int k1 = -1;

	            if (slot != null)
	            {
	                k1 = slot.slotNumber;
	            }

	            if (flag1)
	            {
	                k1 = -999;
	            }

	            if (this.mc.gameSettings.touchscreen && flag1 && this.mc.thePlayer.inventory.getItemStack() == null)
	            {
	                this.mc.displayGuiScreen((GuiScreen)null);
	                return;
	            }

	            if (k1 != -1)
	            {
	                if (this.mc.gameSettings.touchscreen)
	                {
	                    if (slot != null && slot.getHasStack())
	                    {
	                        this.clickedSlot = slot;
	                        this.draggedStack = null;
	                        this.isRightMouseClick = p_73864_3_ == 1;
	                    }
	                    else
	                    {
	                        this.clickedSlot = null;
	                    }
	                }
	                else if (!this.field_147007_t)
	                {
	                    if (this.mc.thePlayer.inventory.getItemStack() == null)
	                    {
	                        if (p_73864_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100)
	                        {
	                            this.handleMouseClick(slot, k1, p_73864_3_, 3);
	                        }
	                        else
	                        {
	                            boolean flag2 = k1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
	                            byte b0 = 0;

	                            if (flag2)
	                            {
	                                this.field_146994_N = slot != null && slot.getHasStack() ? slot.getStack() : null;
	                                b0 = 1;
	                            }
	                            else if (k1 == -999)
	                            {
	                                b0 = 4;
	                            }
	                            this.handleMouseClick(slot, k1, p_73864_3_, b0);
	                        }

	                        this.field_146995_H = true;
	                    }
	                    else
	                    {
	                        this.field_147007_t = true;
	                        this.field_146988_G = p_73864_3_;
	                        this.field_147008_s.clear();

	                        if (p_73864_3_ == 0)
	                        {
	                            this.field_146987_F = 0;
	                        }
	                        else if (p_73864_3_ == 1)
	                        {
	                            this.field_146987_F = 1;
	                        }
	                    }
	                }
	            }
	        }
	        this.field_146998_K = slot;
	        this.field_146997_J = l;
	        this.field_146992_L = p_73864_3_;
	    }

	    /**
	     * lastButtonClicked & timeSinceMouseClick.
	     */
	    protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_)
	    {
	        Slot slot = this.getSlotAtPosition(p_146273_1_, p_146273_2_);
	        ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();
	        if (this.field_147007_t && slot != null && itemstack != null && itemstack.stackSize > this.field_147008_s.size() && Container.func_94527_a(slot, itemstack, true) && slot.isItemValid(itemstack) && this.inventorySlots.canDragIntoSlot(slot))
	        {
	        	if(slot.getClass().getSimpleName().equals("Slot")) {
	                this.field_147008_s.add(slot);
		            this.func_146980_g();
	        	}
	        }
	        
	    }

	    
	    private void func_146980_g()
	    {
	        ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();

	        if (itemstack != null && this.field_147007_t)
	        {
	            this.field_146996_I = itemstack.stackSize;
	            ItemStack itemstack1;
	            int i;

	            for (Iterator iterator = this.field_147008_s.iterator(); iterator.hasNext(); this.field_146996_I -= itemstack1.stackSize - i)
	            {
	                Slot slot = (Slot)iterator.next();
	                itemstack1 = itemstack.copy();
	                i = slot.getStack() == null ? 0 : slot.getStack().stackSize;
	                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack1, i);
	                if (itemstack1.stackSize > itemstack1.getMaxStackSize())
	                {
	                    itemstack1.stackSize = itemstack1.getMaxStackSize();
	                }

	                if (itemstack1.stackSize > slot.getSlotStackLimit())
	                {
	                    itemstack1.stackSize = slot.getSlotStackLimit();
	                }
	            }
	        }
	    }
	    
	    /**
	     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
	     * mouseMove, which==0 or which==1 is mouseUp
	     */
	    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_)
	    {
	        super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_); //Forge, Call parent to release buttons
	        Slot slot = this.getSlotAtPosition(p_146286_1_, p_146286_2_);
	        int l = this.guiLeft;
	        int i1 = this.guiTop;
	        boolean flag = p_146286_1_ < l || p_146286_2_ < i1 || p_146286_1_ >= l + this.xSize || p_146286_2_ >= i1 + this.ySize;
	        int j1 = -1;

	        if (slot != null)
	        {
	            j1 = slot.slotNumber;
	        }

	        if (flag)
	        {
	            j1 = -999;
	        }
	        Slot slot1;
	        Iterator iterator;
	        if (this.field_146993_M && slot != null && p_146286_3_ == 0 && this.inventorySlots.func_94530_a((ItemStack)null, slot))
	        {
	            if (isShiftKeyDown())
	            {
	                if (slot != null && slot.inventory != null && this.field_146994_N != null)
	                {
	                    iterator = this.inventorySlots.inventorySlots.iterator();
	                    while (iterator.hasNext())
	                    {
	                        slot1 = (Slot)iterator.next();

	                        if (slot1 != null && slot1.canTakeStack(this.mc.thePlayer) && slot1.getHasStack() && slot1.inventory == slot.inventory && Container.func_94527_a(slot1, this.field_146994_N, true))
	                        {
	                            this.handleMouseClick(slot1, slot1.slotNumber, p_146286_3_, 1);
	                        }
	                    }
	                }
	            }
	            else
	            {
	                this.handleMouseClick(slot, j1, p_146286_3_, 6);
	            }

	            this.field_146993_M = false;
	            this.field_146997_J = 0L;
	        }
	        else
	        {
	            if (this.field_147007_t && this.field_146988_G != p_146286_3_)
	            {
	                this.field_147007_t = false;
	                this.field_147008_s.clear();
	                this.field_146995_H = true;
	                return;
	            }
	            if (this.field_146995_H)
	            {
	                this.field_146995_H = false;
	                return;
	            }
	            boolean flag1;

	            if (this.field_147007_t && !this.field_147008_s.isEmpty())
	            {
	                this.handleMouseClick((Slot)null, -999, Container.func_94534_d(0, this.field_146987_F), 5);
	                iterator = this.field_147008_s.iterator();
	                while (iterator.hasNext())
	                {
	                    slot1 = (Slot)iterator.next();
	                    this.handleMouseClick(slot1, slot1.slotNumber, Container.func_94534_d(1, this.field_146987_F), 5);
	                }

	                this.handleMouseClick((Slot)null, -999, Container.func_94534_d(2, this.field_146987_F), 5);
	            }
	            else if (this.mc.thePlayer.inventory.getItemStack() != null)
	            {
	                if (p_146286_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100)
	                {
	                    this.handleMouseClick(slot, j1, p_146286_3_, 3);
	                }
	                else
	                {
	                    flag1 = j1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));

	                    if (flag1)
	                    {
	                        this.field_146994_N = slot != null && slot.getHasStack() ? slot.getStack() : null;
	                    }
	                    try {
		                	if(invPlayer.getItemStack() != null && slot != null) {
		                		ItemStack item = invPlayer.getItemStack();
		                		if(item != null) {
		                			if(slot.isItemValid(item)) {
			                			this.handleMouseClick(slot, j1, p_146286_3_, flag1 ? 1 : 0);
			                		}else {
			                			Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + "Невозможно установить"));
			                		}
		                		}
		                	}else if (slot == null) {
		                		 this.handleMouseClick(slot, j1, p_146286_3_, flag1 ? 1 : 0);
		                	}
						} catch (Exception e) {
							System.out.println(e);
						}
	  
	                }
	            }
	        }

	        if (this.mc.thePlayer.inventory.getItemStack() == null)
	        {
	            this.field_146997_J = 0L;
	        }

	        this.field_147007_t = false;
	    }

	    /**
	     * Returns if the passed mouse position is over the specified slot.
	     */
	    private boolean isMouseOverSlot(Slot p_146981_1_, int p_146981_2_, int p_146981_3_)
	    {
            int posX = getRightElementPosX(p_146981_1_.xDisplayPosition * 3.815f) + getRightElementPosX(470);
            int size = getRightElementPosX(62f);
            int posY = getRightElementPosY(p_146981_1_.yDisplayPosition * 3.95f) + getRightElementPosY(71);
	    	return this.isHover(posX, posY, size, size, p_146981_2_, p_146981_3_);
	    //    return this.func_146978_c((p_146981_1_.xDisplayPosition), p_146981_1_.yDisplayPosition, 16, 16, p_146981_2_, p_146981_3_);
	    }

	    protected boolean isHover(int x, int y, int size, int p_146978_4_, int p_146978_5_, int p_146978_6_)
	    {
	        return p_146978_5_ >= x - 1 && p_146978_5_ < x + size + 1 && p_146978_6_ >= y - 1 && p_146978_6_ < y + p_146978_4_ + 1;
	    }

	    protected void handleMouseClick(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_)
	    {
	        if (p_146984_1_ != null)
	        {
	            p_146984_2_ = p_146984_1_.slotNumber;
	        }
	        this.mc.playerController.windowClick(this.inventorySlots.windowId, p_146984_2_, p_146984_3_, p_146984_4_, this.mc.thePlayer);
	    }

	    /**
	     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
	     */
	    protected void keyTyped(char p_73869_1_, int p_73869_2_)
	    {
	    	
	        if (p_73869_2_ == 1 || p_73869_2_ == this.mc.gameSettings.keyBindInventory.getKeyCode())
	        {
	            this.mc.thePlayer.closeScreen();
	        }

	        this.checkHotbarKeys(p_73869_2_);
	        if (this.theSlot != null && this.theSlot.getHasStack())
	        {
	            if (p_73869_2_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode())
	            {
	                this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
	            }
	            else if (p_73869_2_ == this.mc.gameSettings.keyBindDrop.getKeyCode())
	            {
	                this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, 4);
	            }
	        }
	    }

	    /**
	     * This function is what controls the hotbar shortcut check when you press a number key when hovering a stack.
	     */
	    
	    private void func_146977_a(Slot p_146977_1_)
	    {
	        int i = p_146977_1_.xDisplayPosition;
	        int j = p_146977_1_.yDisplayPosition;
	        ItemStack itemstack = p_146977_1_.getStack();
	        boolean flag = false;
	        boolean flag1 = p_146977_1_ == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
	        ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
	        String s = null;

	        if (p_146977_1_ == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null)
	        {
	            itemstack = itemstack.copy();
	            itemstack.stackSize /= 2;
	        }
	        else if (this.field_147007_t && this.field_147008_s.contains(p_146977_1_) && itemstack1 != null)
	        {
	            if (this.field_147008_s.size() == 1)
	            {
	                return;
	            }

	            if (Container.func_94527_a(p_146977_1_, itemstack1, true) && this.inventorySlots.canDragIntoSlot(p_146977_1_))
	            {
	                itemstack = itemstack1.copy();
	                flag = true;
	                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack, p_146977_1_.getStack() == null ? 0 : p_146977_1_.getStack().stackSize);

	                if (itemstack.stackSize > itemstack.getMaxStackSize())
	                {
	                    s = EnumChatFormatting.YELLOW + "" + itemstack.getMaxStackSize();
	                    itemstack.stackSize = itemstack.getMaxStackSize();
	                }

	                if (itemstack.stackSize > p_146977_1_.getSlotStackLimit())
	                {
	                    s = EnumChatFormatting.YELLOW + "" + p_146977_1_.getSlotStackLimit();
	                    itemstack.stackSize = p_146977_1_.getSlotStackLimit();
	                }
	            }
	            else
	            {
	                this.field_147008_s.remove(p_146977_1_);
	                this.func_146980_g();
	            }
	        }

	        this.zLevel = 100.0F;
	        itemRender.zLevel = 100.0F;

	        if (itemstack == null)
	        {
	            IIcon iicon = p_146977_1_.getBackgroundIconIndex();

	            if (iicon != null)
	            {
	                GL11.glDisable(GL11.GL_LIGHTING);
	                GL11.glEnable(GL11.GL_BLEND); // Forge: Blending needs to be enabled for this.
	                this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
	                this.drawTexturedModelRectFromIcon(i, j, iicon, 16, 16);
	                GL11.glDisable(GL11.GL_BLEND); // Forge: And clean that up
	                GL11.glEnable(GL11.GL_LIGHTING);
	                flag1 = true;
	            }
	        }

	        if (!flag1)
	        {
	            if (flag)
	            {
	                drawRect(getRightElementPosX(i * 3.84f) + getRightElementPosX(469.5f), getRightElementPosY(j * 3.95f) + getRightElementPosY(70.5f), getRightElementPosX(i * 3.85f) + getRightElementPosX(473) + getRightElementPosX(55), getRightElementPosY(j * 3.95f) + getRightElementPosY(79) + getRightElementPosY(56), -2130706433);
	            }

	            GL11.glEnable(GL11.GL_DEPTH_TEST);
	            drawItemStack(getRightElementPosX(i * 3.84f) + getRightElementPosX(474), getRightElementPosY(j * 3.95f) + getRightElementPosY(78), itemstack);
	     }

	        itemRender.zLevel = 0.0F;
	        this.zLevel = 0.0F;
	    }
	    
	    
	    
	    protected boolean checkHotbarKeys(int p_146983_1_)
	    {
	        if (this.mc.thePlayer.inventory.getItemStack() == null && this.theSlot != null)
	        {
	            for (int j = 0; j < 9; ++j)
	            {
	                if (p_146983_1_ == this.mc.gameSettings.keyBindsHotbar[j].getKeyCode())
	                {
	                    this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, j, 2);
	                    return true;
	                }
	            }
	        }

	        return false;
	    }
	    
	    private Slot getSlotAtPosition(int p_146975_1_, int p_146975_2_)
	    {
	        for (int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k)
	        {
	            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(k);

	            if (this.isMouseOverSlot(slot, p_146975_1_, p_146975_2_))
	            {
	                return slot;
	            }
	        }

	        return null;
	    }

	    

	    /**
	     * Called when the screen is unloaded. Used to disable keyboard repeat events
	     */
	    public void onGuiClosed()
	    {
	        if (this.mc.thePlayer != null)
	        {
	            this.inventorySlots.onContainerClosed(this.mc.thePlayer);
	        }
	    }
	    
	    public void updateScreen()
	    {
	        super.updateScreen();

	        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
	        {
	            this.mc.thePlayer.closeScreen();
	        }
	    }
	
	
	


	    
}
