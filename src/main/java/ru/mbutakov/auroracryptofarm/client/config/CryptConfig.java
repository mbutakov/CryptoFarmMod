package ru.mbutakov.auroracryptofarm.client.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import ru.mbutakov.auroracryptofarm.Main;

@SideOnly(Side.CLIENT)
public class CryptConfig {

	public static File file = new File(Minecraft.getMinecraft().mcDataDir, Main.MODID + "." + Main.MODID);

	public static boolean blur = true;
	
	public static void save() {
		try (FileOutputStream out = new FileOutputStream(file)) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("blur", blur);
			CompressedStreamTools.writeCompressed(tag, out);
		} catch (IOException e) {
			System.out.println("Failed to save client data.");
			e.printStackTrace();
		}
	}

	public static void load() {
		try (FileInputStream in = new FileInputStream(file)) {
			file.canRead();
			NBTTagCompound tag = CompressedStreamTools.readCompressed(in);
			blur = tag.getBoolean("blur");
		} catch (IOException e) {
			System.out.println("Failed to load client data.");
			e.printStackTrace();
		}
	}
}