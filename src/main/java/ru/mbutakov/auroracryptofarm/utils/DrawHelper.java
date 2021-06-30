/*    */ package ru.mbutakov.auroracryptofarm.utils;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class DrawHelper {
/* 13 */   public static float texture_f = 0.00390625F;
/*    */   
/*    */   public static final float TEXTURE_F_DEFAULT = 0.00390625F;
/*    */   
/*    */   public static void drawStringWithScale(String text, float x, float y, int color, float scaleFactor) {
/* 17 */     GL11.glPushMatrix();
/* 18 */     GL11.glTranslatef(x, y, 0.0F);
/* 19 */     GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
/* 20 */     (Minecraft.getMinecraft()).fontRenderer.drawString(text, 0, 0, color);
/* 21 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public static void drawCenteredStringWithScale(String text, float x, float y, int color, float scaleFactor) {
/* 25 */     GL11.glPushMatrix();
/* 26 */     GL11.glTranslatef(x - (Minecraft.getMinecraft()).fontRenderer.getStringWidth(text) / 2.0F * scaleFactor, y, 0.0F);
/* 27 */     GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
/* 28 */     (Minecraft.getMinecraft()).fontRenderer.drawString(text, 0, 0, color);
/* 29 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public static void drawTexturedModalRect(double x, double w, double h, double y, double u, double v, double tw, double th) {
/* 33 */     float f = texture_f;
/* 34 */     Tessellator tessellator = Tessellator.instance;
/* 35 */     tessellator.startDrawingQuads();
/* 36 */     tessellator.addVertexWithUV(x, y + h, 0.0D, ((float)u * f), ((float)(v + th) * f));
/* 37 */     tessellator.addVertexWithUV(x + w, y + h, 0.0D, ((float)(u + tw) * f), ((float)(v + th) * f));
/* 38 */     tessellator.addVertexWithUV(x + w, y, 0.0D, ((float)(u + tw) * f), ((float)v * f));
/* 39 */     tessellator.addVertexWithUV(x, y, 0.0D, ((float)u * f), ((float)v * f));
/* 40 */     tessellator.draw();
/*    */   }
/*    */   
/*    */   public static void drawCustom(int x, int width, int height, int y) {
/* 44 */     Tessellator tessellator = Tessellator.instance;
/* 45 */     GL11.glEnable(3042);
/* 46 */     OpenGlHelper.glBlendFunc(770, 771, 1, 0);
/* 47 */     tessellator.startDrawingQuads();
/* 48 */     tessellator.addVertexWithUV(x, (y + height), 0.0D, 0.0D, 1.0D);
/* 49 */     tessellator.addVertexWithUV((x + width), (y + height), 0.0D, 1.0D, 1.0D);
/* 50 */     tessellator.addVertexWithUV((x + width), y, 0.0D, 1.0D, 0.0D);
/* 51 */     tessellator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
/* 52 */     tessellator.draw();
/* 53 */     GL11.glDisable(3042);
/*    */   }
/*    */ }
