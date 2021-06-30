package ru.mbutakov.auroracryptofarm.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import ru.mbutakov.auroracryptofarm.Main;

public class FontUtils {

	public static FontContainer FontMonsterrat;
	public static FontContainer FontMonsterratSemi;
	
	public static void registerFont() {
		FontMonsterrat = new FontContainer("monsterrat", 18);
		FontMonsterratSemi = new FontContainer("Montserrat-SemiBold",18);
	}
	
	public static class FontContainer {
		private StringCache textFont = null;
		public boolean useCustomFont = true;

		private FontContainer() {

		}

		public FontContainer(String fontType, int fontSize) {
			textFont = new StringCache();
			textFont.setDefaultFont("Arial", fontSize, true);
			useCustomFont = !fontType.equalsIgnoreCase("minecraft");
			try {
				if (!useCustomFont || fontType.isEmpty() || fontType.equalsIgnoreCase("default"))
					textFont.setCustomFont(new ResourceLocation(Main.MODID, fontType+".ttf"), fontSize, true);
				else
					textFont.setDefaultFont(fontType, fontSize, true);
			} catch (Exception e) {

			}
		}

		public int height() {
			if (useCustomFont)
				return textFont.fontHeight;
			return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
		}

		public int width(String text) {
			if (useCustomFont)
				return textFont.getStringWidth(text);
			return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		}

		public FontContainer copy() {
			FontContainer font = new FontContainer();
			font.textFont = textFont;
			font.useCustomFont = useCustomFont;
			return font;
		}

		public void drawString(String text, int x, int y, int color) {
			if (useCustomFont) {
				textFont.renderString(text, x, y, color, true);
				textFont.renderString(text, x, y, color, false);
			} else {
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, x, y, color);
			}
		}

		public String getName() {
			if (!useCustomFont)
				return "Minecraft";
			return textFont.usedFont().getFontName();
		}
	}
		
}
