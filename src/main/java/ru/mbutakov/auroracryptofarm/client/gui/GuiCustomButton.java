package ru.mbutakov.auroracryptofarm.client.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import ru.mbutakov.auroracryptofarm.client.ClientEvents;
import ru.mbutakov.auroracryptofarm.utils.GuiUtils;

public class GuiCustomButton extends GuiButton
{
    public int isOver;
    public ItemStack renderStack;
    public boolean drawBackground;
    public boolean drawShadow;
    public String buttonColor;
    public boolean centeredText;
    public boolean soundPlayed;
    private ResourceLocation iconTexture;
    private int fade;
    private int xMovement;
    private boolean animationStarted;
    private int toolTipY;
    private String toolTip;
    private boolean showToolTip;
    private Color toolTipColor;
    
    public GuiCustomButton(final int id, final int x, final int y, final int width, final int height, final String displayString) {
        super(id, x, y, width, height, displayString);
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = 0;
        this.animationStarted = true;
        this.showToolTip = false;
    }
    
    public GuiCustomButton(final int id, final int x, final int y, final int width, final int height, final String displayString, final String givenToolTip, final Color givenColor) {
        super(id, x, y, width, height, displayString);
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = 0;
        this.animationStarted = true;
        this.showToolTip = false;
        this.toolTip = givenToolTip;
        this.showToolTip = true;
        this.toolTipColor = givenColor;
    }
    
    public GuiCustomButton(final int id, final int x, final int y, final ItemStack renderStack) {
        super(id, x, y, 18, 18, "");
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = 0;
        this.animationStarted = true;
        this.showToolTip = false;
        this.renderStack = renderStack;
    }
    
    public GuiCustomButton(final int id, final int x, final int y, final int width, final int height, final String par6, final String buttonColor) {
        super(id, x, y, width, height, par6);
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = 0;
        this.animationStarted = true;
        this.showToolTip = false;
        this.buttonColor = buttonColor;
    }
    
    public GuiCustomButton(final int id, final int x, final int y, final int width, final int height, final String par6, final String buttonColor,int fade) {
        super(id, x, y, width, height, par6);
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = fade;
        this.animationStarted = true;
        this.showToolTip = false;
        this.buttonColor = buttonColor;
    }
    
    public GuiCustomButton(final int id, final int x, final int y, final int width, final int height, final String par6, final String buttonColor, final String givenToolTip, final Color givenColor) {
        super(id, x, y, width, height, par6);
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = 0;
        this.animationStarted = true;
        this.showToolTip = false;
        this.buttonColor = buttonColor;
        this.toolTip = givenToolTip;
        this.showToolTip = true;
        this.toolTipColor = givenColor;
    }
    
    public GuiCustomButton(final int id, final int x, final int y, final int width, final int height, final ResourceLocation iconTexture) {
        super(id, x, y, width, height, "");
        this.isOver = 2;
        this.renderStack = null;
        this.drawBackground = true;
        this.drawShadow = true;
        this.buttonColor = "0x000000";
        this.centeredText = true;
        this.soundPlayed = true;
        this.iconTexture = null;
        this.fade = 0;
        this.animationStarted = true;
        this.showToolTip = false;
        this.iconTexture = iconTexture;
    }
    
    public void drawButton(final Minecraft minecraft, final int mouseX, final int mouseY) {
        if (this.visible) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
            this.field_146123_n = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            this.isOver = this.getHoverState(this.field_146123_n);
            final int toolTipWidth = minecraft.fontRenderer.getStringWidth(this.toolTip);
            if (this.drawBackground) {
            	if (this.isOver == 2) {
                    GuiUtils.drawRectWithShadow(this.xPosition, this.yPosition, this.width, this.height, this.buttonColor, 1f - ClientEvents.timeOpen / 100);
            	}else {
            	      GuiUtils.drawRectWithShadow(this.xPosition, this.yPosition, this.width, this.height, this.buttonColor, 1 - ClientEvents.timeOpen / 100);
                  	
            	}
                
            }
            this.mouseDragged(minecraft, mouseX, mouseY);
            String displayText = this.displayString;
            if (this.iconTexture != null) {
                if (this.isOver == 2) {
                    GuiUtils.renderColor(8421504);
                }
                GuiUtils.drawImage(this.xPosition + this.width / 2 - 8, this.yPosition + (this.height - 8) - 10, this.iconTexture, 16.0, 16.0);
                GL11.glColor3f(1.0f, 1.0f, 1.0f);
                return;
            }
            if (this.renderStack != null) {
                GuiUtils.renderItemStackIntoGUI(this.renderStack, this.xPosition, this.yPosition + 1);
                return;
            }
            if (!this.enabled) {
                displayText = EnumChatFormatting.GRAY + displayText;
            }
            if (!this.centeredText) {
                GuiUtils.renderTextWithShadow(displayText, this.xPosition + 2, this.yPosition + (this.height - 8) / 2, (this.isOver == 2) ? 8421504 : 16777215);
                return;
            }
            if (this.showToolTip && this.isOver == 2 ) {
                GuiUtils.drawRectWithShadow2(mouseX, mouseY - 10, this.toolTipY, 10, this.toolTipColor, 100);
                if (this.toolTipY < toolTipWidth + 2) {
                    final int toolTipGap = toolTipWidth + 2 - this.toolTipY;
                    if (toolTipGap >= 10) {
                        this.toolTipY += 10;
                    }
                    else {
                        ++this.toolTipY;
                    }
                }
                else if (this.toolTipY > toolTipWidth + 2) {
                    --this.toolTipY;
                }
                if (this.toolTipY >= toolTipWidth + 2) {
                    GuiUtils.renderText(this.toolTip, mouseX + 1, mouseY - 9);
                }
            }
            
            GuiUtils.renderCenteredTextWithShadow(displayText, this.xPosition + this.width / 2 + this.xMovement, this.yPosition + (this.height - 8) / 2, (this.isOver == 2) ? 0xFF000 : 16777215);
        }
    }
    
    public void func_146113_a(final SoundHandler soundHandler) {
        soundHandler.playSound((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0f));
    }
}
