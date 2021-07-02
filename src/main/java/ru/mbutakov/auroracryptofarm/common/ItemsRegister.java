package ru.mbutakov.auroracryptofarm.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.tests.xml.Inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.items.CpuItem;
import ru.mbutakov.auroracryptofarm.common.items.FanItem;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;
import ru.mbutakov.auroracryptofarm.common.items.UsbflashItem;
import ru.mbutakov.auroracryptofarm.common.items.GpuItem;
import ru.mbutakov.auroracryptofarm.utils.EnumFormatMotherboard;
import ru.mbutakov.auroracryptofarm.utils.EnumItemsPc;

public class ItemsRegister {
	//флешка
	public static Item
	item_usbflash;
	
	public static ArrayList<Item> listItems = new ArrayList<>();
	
	
	public static void registerItems() {
		item_usbflash = new UsbflashItem("Usb-Flash","usbflash",Main.cryptoTab);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerName(Item item,String nameItem) {
		if(FMLCommonHandler.instance().getSide().isClient()) {
			LanguageRegistry.addName(item, nameItem);
		}
	}
	
	public static void syncDir() {
	      for(File file : Main.auroraDir.listFiles()) {
	       	 try {
	       		if(file.getName().startsWith("M")) {
	       			loadItems(file,EnumItemsPc.MOTHERBOARD);
	       		}
	       		if(file.getName().startsWith("G")) {
	       			loadItems(file,EnumItemsPc.GPU);
	       		}
	       		if(file.getName().startsWith("F")) {
	       			loadItems(file,EnumItemsPc.FAN);
	       		}
	       		if(file.getName().startsWith("C")) {
	       			loadItems(file,EnumItemsPc.CPU);
	       		}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	      }
	}
	
	public static void loadItems(File fileLoad,EnumItemsPc itemsPc) throws IOException {
      try {
          BufferedReader reader = new BufferedReader(new FileReader(fileLoad));
          ArrayList<String> listOfVariables = new ArrayList<>();
          String line;
          if(itemsPc == EnumItemsPc.CPU) {
              int count = 0;
              while((line= reader.readLine())!=null){
                  String[] val = line.split(":");
                  String members = val[1];
                  listOfVariables.add(members);
                  count+=1;
              }
              String name = listOfVariables.get(0);
              String nameTexture = listOfVariables.get(1);
              double cof = Double.parseDouble(listOfVariables.get(2));
              int chip = Integer.parseInt(listOfVariables.get(3));
              int maxDamage = Integer.parseInt(listOfVariables.get(4));
              listItems.add(new CpuItem(name, nameTexture, Main.cryptoTab, cof, chip, maxDamage));
          }
          if(itemsPc == EnumItemsPc.FAN) {
              int count = 0;
              while((line= reader.readLine())!=null){
                  String[] val = line.split(":");
                  String members = val[1];
                  listOfVariables.add(members);
                  count+=1;
              }
              String name = listOfVariables.get(0);
              String nameTexture = listOfVariables.get(1);
              int maxDamage = Integer.parseInt(listOfVariables.get(2));
              listItems.add(new FanItem(name, nameTexture, Main.cryptoTab, maxDamage));
          }
          
          if(itemsPc == EnumItemsPc.GPU) {
              int count = 0;
              while((line= reader.readLine())!=null){
                  String[] val = line.split(":");
                  String members = val[1];
                  listOfVariables.add(members);
                  count+=1;
              }
              String name = listOfVariables.get(0);
              String nameTexture = listOfVariables.get(1);
              double coinAdd = Double.parseDouble(listOfVariables.get(2));
              int maxDamage = Integer.parseInt(listOfVariables.get(3));
              listItems.add(new GpuItem(name, nameTexture, Main.cryptoTab, coinAdd, maxDamage));
          }
          if(itemsPc == EnumItemsPc.MOTHERBOARD) {
              int count = 0;
              while((line= reader.readLine())!=null){
                  String[] val = line.split(":");
                  String members = val[1];
                  listOfVariables.add(members);
                  count+=1;
              }
              String name = listOfVariables.get(0);
              String nameTexture = listOfVariables.get(1);
              int countGpu = Integer.parseInt(listOfVariables.get(2));
              int chip = Integer.parseInt(listOfVariables.get(3));
              EnumFormatMotherboard format = EnumFormatMotherboard.valueOf(listOfVariables.get(4));
              listItems.add(new MotherboardItem(name, nameTexture, Main.cryptoTab, chip, countGpu, format));
          }

      }catch(Exception ex){
          ex.printStackTrace();
      }
	}
	
	

        
}
