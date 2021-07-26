package ru.mbutakov.auroracryptofarm.utils;

import org.lwjgl.opengl.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import java.awt.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.*;
import ru.mbutakov.auroracryptofarm.client.gui.GuiPc;

import java.io.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.*;

public class GuiUtils
{
    private static final ResourceLocation beaconBeam;
    public static int gameColor;
    public static boolean dM;
    public static Tessellator tesselator = Tessellator.instance;
    private static Minecraft mc = Minecraft.getMinecraft();

    
    public static void drawScissorBox(final int x, final int y, final int width, final int height) {
        GL11.glEnable(3089);
        GL11.glScissor(x, y, width, height);
    }
    
    public static void disableScissorBox() {
        GL11.glDisable(3089);
    }
    
    public static void renderCenteredText(final String text, final int posX, final int posY) {
        renderCenteredText(text, posX, posY, GuiUtils.gameColor);
    }
    
    public static void renderCenteredTextWithShadow(final String text, final int posX, final int posY) {
        renderCenteredTextWithShadow(text, posX, posY, GuiUtils.gameColor);
    }
    
    public static void renderCenteredText(final String text, final int posX, final int posY, final int par4) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, par4);
    }
    
    
    public static void drawImageCentered(double givenX, double givenY, ResourceLocation givenTexture, double givenWidth, double givenHeight) {
      GL11.glPushMatrix();
      drawImage(givenX - givenWidth / 2.0D, givenY - givenHeight / 2.0D, givenTexture, givenWidth, givenHeight);
      GL11.glPopMatrix();
    }

      
      public static void drawImageCenteredScaledWithAlpha(double x, double y, ResourceLocation image, double width, double height, double scale, double givenAlpha) {
        GL11.glPushMatrix();
        GL11.glColor4d(1.0D, 1.0D, 1.0D, givenAlpha);
        mc.renderEngine.bindTexture(image);
        GL11.glTranslated(-width / 2.0D * scale * scale, -height / 2.0D * scale * scale, 0.0D);
        GL11.glScaled(scale, scale, scale);
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        tesselator.startDrawingQuads();
        tesselator.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
        tesselator.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
        tesselator.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
        tesselator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
        tesselator.draw();
        GL11.glDisable(3042);
        GL11.glDisable(2832);
        GL11.glPopMatrix();
      }


    
          public static void renderPositionedImageInViewWithDepth(ResourceLocation par1, double par2, double par3, double par4, float par5, float width, float height, float givenAlpha)
          {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            GL11.glPushMatrix();
            GL11.glTranslated(par2, par3, par4);
            GL11.glTranslated(-RenderManager.instance.viewerPosX, -RenderManager.instance.viewerPosY, -RenderManager.instance.viewerPosZ);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-player.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(player.rotationPitch, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-0.03F, -0.03F, 0.03F);
            renderImageTransparent(-width / 2.0F, -height / 2.0F, par1, width, height, givenAlpha);
            GL11.glPopMatrix();
          }
          
          public static void renderImageTransparent(double x, double y, ResourceLocation image, double width, double height) {
              mc.renderEngine.bindTexture(image);
              GL11.glColor4d(255.0D, 255.0D, 255.0D, 255.0D);
             GL11.glEnable(3042);
              GL11.glEnable(2832);
              tesselator.setColorRGBA_F(255.0F, 255.0F, 255.0F, 255.0F);
             tesselator.startDrawingQuads();
              tesselator.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
              tesselator.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
              tesselator.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
              tesselator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
              tesselator.draw();
              GL11.glDisable(2832);
              GL11.glDisable(3042);
            }
            
            public static void renderImageTransparent(double x, double y, ResourceLocation image, double width, double height, double alpha) {
              mc.renderEngine.bindTexture(image);
              GL11.glColor4d(1.0D, 1.0D, 1.0D, alpha);
              GL11.glEnable(3042);
              GL11.glEnable(2832);
              GL11.glBlendFunc(770, 771);
              GL11.glEnable(3008);
              tesselator.setColorRGBA_F(255.0F, 255.0F, 255.0F, 0.5F);
              tesselator.startDrawingQuads();
              tesselator.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
              tesselator.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
              tesselator.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
              tesselator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
              tesselator.draw();
              GL11.glDisable(2832);
             GL11.glDisable(3042);
           }


    
    public static void drawCustom(int x, int width, int height, int y) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, 0.0D, 0, 1);
        tessellator.addVertexWithUV(x + width, y + height, 0.0D, 1, 1);
        tessellator.addVertexWithUV(x + width, y, 0.0D, 1, 0);
        tessellator.addVertexWithUV(x, y, 0.0D, 0, 0);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void renderCenteredTextWithShadow(final String text, final int posX, final int posY, final int par4) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawStringWithShadow(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, par4);
     
    }
    
    public static void renderText(final String text, final int posX, final int posY) {
        renderText(text, posX, posY, GuiUtils.gameColor);
    }
    
    public static void renderTextWithShadow(final String text, final int posX, final int posY) {
        renderTextWithShadow(text, posX, posY, GuiUtils.gameColor);
    }
    
    public static void renderSplitText(final String text, final int posX, final int posY, final int wrapWidth) {
        renderSplitText(text, posX, posY, wrapWidth, GuiUtils.gameColor);
    }
    
    public static void renderText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX, posY, color);
    }
    
    public static void drawTextWithOutline(final String text, final int x, final int y, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        final FontRenderer fr = mc.fontRenderer;
        fr.drawString(text, x - 1, y + 1, 0);
        fr.drawString(text, x, y + 1, 0);
        fr.drawString(text, x + 1, y + 1, 0);
        fr.drawString(text, x - 1, y, 0);
        fr.drawString(text, x + 1, y, 0);
        fr.drawString(text, x - 1, y - 1, 0);
        fr.drawString(text, x, y - 1, 0);
        fr.drawString(text, x + 1, y - 1, 0);
        fr.drawString(text, x, y, color);
    }
    
    public static void renderSmokeNadeSmoke(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final int width, final int height, final String color, final double alpha) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final FontRenderer fontrenderer = mc.fontRenderer;
        final float f122 = 1.8f;
        final float scale2 = 0.02f;
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glScalef(-scale2, -scale2, scale2);
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        renderColor(color);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void renderCenteredTextScaled(final String text, final int posX, final int posY, final double par4, final int givenColor) {
        final Minecraft mc = Minecraft.getMinecraft();
        final double width = mc.fontRenderer.getStringWidth(text) / 2 * par4;
        GL11.glPushMatrix();
        GL11.glTranslated(posX - width, (double)posY, 0.0);
        GL11.glScaled(par4, par4, par4);
        mc.fontRenderer.drawString(text, 0, 0, givenColor);
        GL11.glPopMatrix();
    }
    
    public static void renderCenteredTextScaledWithOutlineFade(final String text, final int posX, final int posY, final double par4, final int givenColor, final int givenFade) {
        final Minecraft mc = Minecraft.getMinecraft();
        final double width = mc.fontRenderer.getStringWidth(text) / 2 * par4;
        GL11.glPushMatrix();
        GL11.glColor4f(255.0f, 255.0f, 255.0f, (float)givenFade);
        GL11.glTranslated(posX - width, (double)posY, 0.0);
        GL11.glScaled(par4, par4, par4);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, -1, -1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 1, -1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, -1, 1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 1, 1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 0, -1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, -1, 0, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 1, 0, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 0, 1, 0);
        mc.fontRenderer.drawString(text, 0, 0, givenColor);
        GL11.glPopMatrix();
    }
    
    public static void renderCenteredTextScaledWithOutline(final String text, final int posX, final int posY, final double par4, final int givenColor) {
        final Minecraft mc = Minecraft.getMinecraft();
        final double width = mc.fontRenderer.getStringWidth(text) / 2 * par4;
        GL11.glPushMatrix();
        GL11.glTranslated(posX - width, (double)posY, 0.0);
        GL11.glScaled(par4, par4, par4);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, -1, -1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 1, -1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, -1, 1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 1, 1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 0, -1, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, -1, 0, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 1, 0, 0);
        mc.fontRenderer.drawString(EnumChatFormatting.BLACK + text, 0, 1, 0);
        mc.fontRenderer.drawString(text, 0, 0, givenColor);
        GL11.glPopMatrix();
    }
    
    public static void drawTextScaled(final String text, final int posX, final int posY, final double par4, final int par5) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, 0.0);
        GL11.glScaled(par4, par4, par4);
        renderText(text, 0, 0, par5);
        GL11.glPopMatrix();
    }
    
    public static void renderTextWithShadow(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawStringWithShadow(text, posX, posY, color);
    }
    
    public static void renderSplitText(final String text, final int posX, final int posY, final int wrapWidth, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawSplitString(text, posX, posY, wrapWidth, color);
    }
    
    public static void renderItemStackIntoGUI(final ItemStack itemstack, final int posX, final int posY) {
        GL11.glPushMatrix();
        final RenderItem itemRenderer = new RenderItem();
        itemRenderer.renderWithColor = true;
        itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), itemstack, posX, posY);
        itemRenderer.renderWithColor = false;
        GL11.glDisable(2896);
        GL11.glPopMatrix();
    }
    
    public static void renderColor(final int par1) {
        final Color color = Color.decode("" + par1);
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        GL11.glColor3f(red, green, blue);
    }
    
    public static void renderColor(final String par1) {
        final Color color = Color.decode("" + par1);
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        GL11.glColor3f(red, green, blue);
    }
    
    public static void drawImageNoColorChange(final double x, final double y, final ResourceLocation image, final double width, final double height) {
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        final Tessellator tess = Tessellator.instance;
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y + height, 0.0, 0.0, 1.0);
        tess.addVertexWithUV(x + width, y + height, 0.0, 1.0, 1.0);
        tess.addVertexWithUV(x + width, y, 0.0, 1.0, 0.0);
        tess.addVertexWithUV(x, y, 0.0, 0.0, 0.0);
        tess.draw();
        GL11.glDisable(3042);
        GL11.glDisable(2832);
    }
    
    
    public static void drawImageCenteredScaled(final double x, final double y, final ResourceLocation image, final double width, final double height, final double scale) {
        GL11.glPushMatrix();
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        GL11.glTranslated(-width / 2.0 * scale * scale, -height / 2.0 * scale * scale, 0.0);
        GL11.glScaled(scale, scale, scale);
        final Tessellator tess = Tessellator.instance;
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y + height, 0.0, 0.0, 1.0);
        tess.addVertexWithUV(x + width, y + height, 0.0, 1.0, 1.0);
        tess.addVertexWithUV(x + width, y, 0.0, 1.0, 0.0);
        tess.addVertexWithUV(x, y, 0.0, 0.0, 0.0);
        tess.draw();
        GL11.glDisable(3042);
        GL11.glDisable(2832);
        GL11.glPopMatrix();
    }
    
    public static void drawImage(final double x, final double y, final ResourceLocation image, final double width, final double height) {
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        final Tessellator tess = Tessellator.instance;
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y + height, 0.0, 0.0, 1.0);
        tess.addVertexWithUV(x + width, y + height, 0.0, 1.0, 1.0);
        tess.addVertexWithUV(x + width, y, 0.0, 1.0, 0.0);
        tess.addVertexWithUV(x, y, 0.0, 0.0, 0.0);
        tess.draw();
        GL11.glDisable(2832);
    }
    
    public static void drawImageTransparent(double x, double y, ResourceLocation image, double width, double height)
    {
      Minecraft.getMinecraft().renderEngine.bindTexture(image);
      GL11.glColor4d(255.0D, 255.0D, 255.0D, 255.0D);
      Tessellator tess = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glEnable(2832);
      tess.setColorRGBA_F(255.0F, 255.0F, 255.0F, 255.0F);
      tess.startDrawingQuads();
      tess.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
      tess.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
      tess.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
      tess.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
      tess.draw();
      GL11.glDisable(2832);
      GL11.glDisable(3042);
    }
    
    public static void drawImageTransparent(double x, double y, ResourceLocation image, double width, double height, double alpha)
    {
      Minecraft.getMinecraft().renderEngine.bindTexture(image);
      GL11.glColor4d(255.0D, 255.0D, 255.0D, alpha);
      Tessellator tess = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glEnable(2832);
      tess.setColorRGBA_F(255.0F, 0.0F, 0.0F, 0.5F);
      tess.startDrawingQuads();
      tess.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
      tess.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
      tess.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
      tess.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
      tess.draw();
      GL11.glDisable(2832);
      GL11.glDisable(3042);
    }
    
    public static void drawMenuBackground(final double x, final double y, final ResourceLocation image, final double width, final double height) {
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        final Tessellator tess = Tessellator.instance;
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        tess.setColorRGBA_F(255.0f, 0.0f, 0.0f, 0.5f);
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y + height, 0.0, 0.0, 1.0);
        tess.addVertexWithUV(x + width, y + height, 0.0, 1.0, 1.0);
        tess.addVertexWithUV(x + width, y, 0.0, 1.0, 0.0);
        tess.addVertexWithUV(x, y, 0.0, 0.0, 0.0);
        tess.draw();
        GL11.glDisable(2832);
        GL11.glDisable(3042);
    }
    
    
    public static void drawPositionedImageInViewNoRot(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final int width, final int height, final double givenRotX, final double givenRotY, final double givenRotZ) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glRotated(givenRotX, 1.0, 0.0, 0.0);
        GL11.glRotated(givenRotY, 0.0, 1.0, 0.0);
        GL11.glRotated(givenRotZ, 0.0, 0.0, 1.0);
        renderColor(16777215);
        drawImage(-width / 2, -height / 2, par1, width, height);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawFlare(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final float width, final float height, final int givenR, final int givenG, final int givenB) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
        GL11.glColor3f((float)givenR, (float)givenG, (float)givenB);
        drawImageNoColorChange(-width / 2.0f, -height / 2.0f, par1, width, height);
        GL11.glDepthMask(true);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawPositionedImageInView(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final float width, final float height) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        if (GuiUtils.dM) {
            GL11.glDepthMask(false);
        }
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        renderColor(16777215);
        drawImage(-width / 2.0f, -height / 2.0f, par1, width, height);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawPositionedImageInViewWithDepth(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final float width, final float height, final float givenAlpha) {
        final EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3008);
        drawImageTransparent(-width / 2.0f, -height / 2.0f, par1, width, height, givenAlpha);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }
    
    public static void drawPositionedTextInView(final String par1, final double par2, final double par3, final double par4, final float par5) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        if (GuiUtils.dM) {
            GL11.glDepthMask(false);
        }
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        renderColor(16777215);
        drawTextWithOutline(par1, 0, 0, 16777215);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawPositionedTextInViewScaled(final String par1, final double par2, final double par3, final double par4, final float par5, final double scale, final int givenColor) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        if (GuiUtils.dM) {
            GL11.glDepthMask(false);
        }
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        renderColor(16777215);
        renderCenteredTextScaledWithOutline(par1, 0, 0, scale, givenColor);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawPositionedTextInViewScaled(final String par1, final double par2, final double par3, final double par4, final float par5, final double scale) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.thePlayer;
        GL11.glPushMatrix();
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * par5;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * par5;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * par5;
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-d0, -d2, -d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glDisable(2896);
        if (GuiUtils.dM) {
            GL11.glDepthMask(false);
        }
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        renderColor(16777215);
        renderCenteredTextScaledWithOutline(par1, 0, 0, scale, 16777215);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void renderBeam(final World world, final double p_147500_2_, final double p_147500_4_, final double p_147500_6_, final float p_147500_8_) {
        GL11.glAlphaFunc(516, 0.1f);
        final Tessellator tessellator = Tessellator.instance;
        Minecraft.getMinecraft().renderEngine.bindTexture(GuiUtils.beaconBeam);
        GL11.glTexParameterf(3553, 10242, 10497.0f);
        GL11.glTexParameterf(3553, 10243, 10497.0f);
        GL11.glDisable(2896);
        GL11.glDisable(2884);
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        OpenGlHelper.glBlendFunc(770, 1, 1, 0);
        final float f2 = world.getTotalWorldTime() + p_147500_8_;
        final float f3 = -f2 * 0.2f - MathHelper.floor_float(-f2 * 0.1f);
        final byte b0 = 1;
        final double d3 = f2 * 0.025 * (1.0 - (b0 & 0x1) * 2.5);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 255, 255, 32);
        final double d4 = b0 * 0.2;
        final double d5 = 0.5 + Math.cos(d3 + 2.356194490192345) * d4;
        final double d6 = 0.5 + Math.sin(d3 + 2.356194490192345) * d4;
        final double d7 = 0.5 + Math.cos(d3 + 0.7853981633974483) * d4;
        final double d8 = 0.5 + Math.sin(d3 + 0.7853981633974483) * d4;
        final double d9 = 0.5 + Math.cos(d3 + 3.9269908169872414) * d4;
        final double d10 = 0.5 + Math.sin(d3 + 3.9269908169872414) * d4;
        final double d11 = 0.5 + Math.cos(d3 + 5.497787143782138) * d4;
        final double d12 = 0.5 + Math.sin(d3 + 5.497787143782138) * d4;
        final double d13 = 256.0;
        final double d14 = 0.0;
        final double d15 = 1.0;
        final double d16 = -1.0f + f3;
        final double d17 = 256.0 * (0.5 / d4) + d16;
        tessellator.addVertexWithUV(p_147500_2_ + d5, p_147500_4_ + d13, p_147500_6_ + d6, d15, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d5, p_147500_4_, p_147500_6_ + d6, d15, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d7, p_147500_4_, p_147500_6_ + d8, d14, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d7, p_147500_4_ + d13, p_147500_6_ + d8, d14, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d11, p_147500_4_ + d13, p_147500_6_ + d12, d15, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d11, p_147500_4_, p_147500_6_ + d12, d15, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d9, p_147500_4_, p_147500_6_ + d10, d14, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d9, p_147500_4_ + d13, p_147500_6_ + d10, d14, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d7, p_147500_4_ + d13, p_147500_6_ + d8, d15, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d7, p_147500_4_, p_147500_6_ + d8, d15, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d11, p_147500_4_, p_147500_6_ + d12, d14, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d11, p_147500_4_ + d13, p_147500_6_ + d12, d14, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d9, p_147500_4_ + d13, p_147500_6_ + d10, d15, d17);
        tessellator.addVertexWithUV(p_147500_2_ + d9, p_147500_4_, p_147500_6_ + d10, d15, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d5, p_147500_4_, p_147500_6_ + d6, d14, d16);
        tessellator.addVertexWithUV(p_147500_2_ + d5, p_147500_4_ + d13, p_147500_6_ + d6, d14, d17);
        tessellator.draw();
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glDepthMask(false);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 255, 255, 32);
        final double d18 = 0.2;
        final double d19 = 0.2;
        final double d20 = 0.8;
        final double d21 = 0.2;
        final double d22 = 0.2;
        final double d23 = 0.8;
        final double d24 = 0.8;
        final double d25 = 0.8;
        final double d26 = 256.0;
        final double d27 = 0.0;
        final double d28 = 1.0;
        final double d29 = -1.0f + f3;
        final double d30 = 256.0 + d29;
        tessellator.addVertexWithUV(p_147500_2_ + d18, p_147500_4_ + d26, p_147500_6_ + d19, d28, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d18, p_147500_4_, p_147500_6_ + d19, d28, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d20, p_147500_4_, p_147500_6_ + d21, d27, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d20, p_147500_4_ + d26, p_147500_6_ + d21, d27, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d24, p_147500_4_ + d26, p_147500_6_ + d25, d28, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d24, p_147500_4_, p_147500_6_ + d25, d28, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d22, p_147500_4_, p_147500_6_ + d23, d27, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d22, p_147500_4_ + d26, p_147500_6_ + d23, d27, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d20, p_147500_4_ + d26, p_147500_6_ + d21, d28, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d20, p_147500_4_, p_147500_6_ + d21, d28, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d24, p_147500_4_, p_147500_6_ + d25, d27, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d24, p_147500_4_ + d26, p_147500_6_ + d25, d27, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d22, p_147500_4_ + d26, p_147500_6_ + d23, d28, d30);
        tessellator.addVertexWithUV(p_147500_2_ + d22, p_147500_4_, p_147500_6_ + d23, d28, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d18, p_147500_4_, p_147500_6_ + d19, d27, d29);
        tessellator.addVertexWithUV(p_147500_2_ + d18, p_147500_4_ + d26, p_147500_6_ + d19, d27, d30);
        tessellator.draw();
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glDepthMask(true);
    }
    
    
    public static void drawRect(final int x, final int y, final int width, final int height, final String colorString, final float alpha) {
        final Color color = Color.decode(colorString);
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        drawRect(x, y, x + width, y + height, red, green, blue, alpha);
    }
    
    public static void drawRect(final int xStart, final int yStart, final int xEnd, final int yEnd, final int rgb) {
        final Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glColor3b((byte)(rgb >> 16), (byte)(rgb >> 8 & 0xFF), (byte)(rgb & 0xFF));
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)xStart, (double)yEnd, 0.0);
        tessellator.addVertex((double)xEnd, (double)yEnd, 0.0);
        tessellator.addVertex((double)xEnd, (double)yStart, 0.0);
        tessellator.addVertex((double)xStart, (double)yStart, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawRect(final int xStart, final int yStart, final int xEnd, final int yEnd, final float r, final float g, final float b, final float alpha) {
        final Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glColor4f(r, g, b, alpha);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)xStart, (double)yEnd, 0.0);
        tessellator.addVertex((double)xEnd, (double)yEnd, 0.0);
        tessellator.addVertex((double)xEnd, (double)yStart, 0.0);
        tessellator.addVertex((double)xStart, (double)yStart, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawRect2(final int xStart, final int yStart, final int xEnd, final int yEnd, final Color color) {
        final float r = color.getRed() / 255.0f;
        final float g = color.getGreen() / 255.0f;
        final float b = color.getBlue() / 255.0f;
        final float a = color.getAlpha() / 255.0f;
        final Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glColor4f(r, g, b, a);
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)xStart, (double)(yStart + yEnd), 0.0);
        tessellator.addVertex((double)(xStart + xEnd), (double)(yStart + yEnd), 0.0);
        tessellator.addVertex((double)(xStart + xEnd), (double)yStart, 0.0);
        tessellator.addVertex((double)xStart, (double)yStart, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawRectWithShadow2(final int x, final int y, final int width, final int height, final Color color, final int alpha) {
        drawRect2(x - 1, y - 1, width + 2, height + 2, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha / 2));
        drawRect2(x, y, width, height, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
    }
    
    public static void drawRectWithShadow(final int par0, final int par1, final int par2, final int par3, final String par4Hex, final float par5Alpha) {
        drawRect(par0 - 1, par1 - 1, par2 + 2, par3 + 2, "0x000000", 0.2f);
        drawRect(par0, par1, par2, par3, par4Hex, par5Alpha);
    }
    
    public static void drawRectWithShadow2(final int par0, final int par1, final int par2, final int par3, final String par4Hex, final float par5Alpha, final float par6Alpha) {
        drawRect(par0 - 1, par1 - 1, par2 + 2, par3 + 2, "0x000000", par6Alpha);
        drawRect(par0, par1, par2, par3, par4Hex, par5Alpha);
    }
    
    public static boolean isInBox(final int x, final int y, final int width, final int height, final int checkX, final int checkY) {
        return checkX >= x && checkY >= y && checkX <= x + width && checkY <= y + height;
    }
    
    public static void drawTexturedQuadFit(final int width, final int height) {
        final Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(10.0, (double)(10 + height), 1.0, 0.0, 1.0);
        t.addVertexWithUV((double)(10 + width), (double)(10 + height), 1.0, 1.0, 1.0);
        t.addVertexWithUV((double)(10 + width), 10.0, 1.0, 1.0, 0.0);
        t.addVertexWithUV(10.0, 10.0, 1.0, 0.0, 0.0);
        t.draw();
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB par1AxisAlignedBB) {
        final Tessellator var2 = Tessellator.instance;
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(1);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.draw();
    }
    
    public static void drawSelectionBox(final EntityPlayer player, final float particleTicks, final AxisAlignedBB boundingBox, final Color givenColor) {
        GL11.glPushMatrix();
        GL11.glDisable(3008);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final float colorR = givenColor.getRed();
        final float colorG = givenColor.getGreen();
        final float colorB = givenColor.getBlue();
        GL11.glColor4f(colorR, colorG, colorB, 0.7f);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        final double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * particleTicks;
        final double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * particleTicks;
        final double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * particleTicks;
        drawOutlinedBoundingBox(boundingBox.getOffsetBoundingBox(-d0, -d2, -d3));
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 255.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawLine(final int posX1, final int posY1, final int posZ1, final int posX2, final int posY2, final int posZ2, final EntityPlayer player, final float particleTicks, final Color givenColor) {
        GL11.glPushMatrix();
        GL11.glDisable(3008);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final float colorR = givenColor.getRed();
        final float colorG = givenColor.getGreen();
        final float colorB = givenColor.getBlue();
        GL11.glColor4f(colorR, colorG, colorB, 0.7f);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        double var10000 = player.lastTickPosX + (player.posX - player.lastTickPosX) * particleTicks;
        var10000 = player.lastTickPosY + (player.posY - player.lastTickPosY) * particleTicks;
        var10000 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * particleTicks;
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)posX1, (double)posY1, (double)posZ1);
        tessellator.addVertex((double)(posX1 + 2), (double)(posY1 + 2), (double)(posZ1 + 2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)posX2, (double)posY2, (double)posZ2);
        tessellator.addVertex((double)posX2, (double)posY2, (double)posZ2);
        tessellator.draw();
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 255.0f);
        GL11.glPopMatrix();
    }
    
    
    public static String secondsToTimeFormatted(final long sec) {
        final long seconds = sec % 60L;
        long minutes = sec / 60L;
        if (minutes < 60L) {
            return String.format("00:%02d:%02d", minutes, seconds);
        }
        final long hours = minutes / 60L;
        minutes %= 60L;
        if (hours >= 24L) {
            final long days = hours / 24L;
            return String.format("%d days %02d:%02d:%02d", days, hours % 24L, minutes, seconds);
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    public static void renderAABB(final AxisAlignedBB p_76980_0_) {
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.maxY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.maxY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.minY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.minY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.minY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.minY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.maxY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.maxY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.minY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.maxY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.maxY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.minX, p_76980_0_.minY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.minY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.maxY, p_76980_0_.minZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.maxY, p_76980_0_.maxZ);
        tessellator.addVertex(p_76980_0_.maxX, p_76980_0_.minY, p_76980_0_.maxZ);
        tessellator.draw();
    }
    
    public static void renderOffsetAABB(final AxisAlignedBB p_76978_0_, final double p_76978_1_, final double p_76978_3_, final double p_76978_5_) {
        GL11.glDisable(3553);
        final Tessellator tessellator = Tessellator.instance;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.startDrawingQuads();
        tessellator.setTranslation(p_76978_1_, p_76978_3_, p_76978_5_);
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.minZ);
        tessellator.setNormal(0.0f, 0.0f, 1.0f);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.maxZ);
        tessellator.setNormal(0.0f, -1.0f, 0.0f);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.maxZ);
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.minZ);
        tessellator.setNormal(-1.0f, 0.0f, 0.0f);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.minZ);
        tessellator.setNormal(1.0f, 0.0f, 0.0f);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.minZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.maxZ);
        tessellator.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.maxZ);
        tessellator.setTranslation(0.0, 0.0, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
    }
    
    public void drawBoundingBox(final AxisAlignedBB boundingBox) {
        final Tessellator tessellator = Tessellator.instance;
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.draw();
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        tessellator.draw();
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        tessellator.draw();
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        tessellator.draw();
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        tessellator.draw();
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        tessellator.draw();
    }
    
    static {
        beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");
        GuiUtils.gameColor = 16777215;
        GuiUtils.dM = true;
    }
}
