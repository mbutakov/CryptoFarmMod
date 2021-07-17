package ru.mbutakov.auroracryptofarm.common.items;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.ItemsRegister;
import ru.mbutakov.auroracryptofarm.utils.EnumFormatMotherboard;

@Getter
public class MotherboardItem extends Item {

	private int chip;
	private int countVideocard;
	private EnumFormatMotherboard format;
	
	public MotherboardItem(String name,String nameTexture,CreativeTabs tab,int chip,int countVidiocard,EnumFormatMotherboard format) {
		super();
		setCreativeTab(tab);
		setTextureName(Main.MODID + ":" + nameTexture);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		this.chip = chip;
		this.countVideocard = countVidiocard;
		this.format = format;
		GameRegistry.registerItem(this,name);
		Main.proxy.registerName(this, name);
		setNoRepair();
	}
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
       	list.add(EnumChatFormatting.WHITE + "Форм-фактор: " +  format.toString());
    	list.add(EnumChatFormatting.WHITE + "Подходящий чипсет: " +  chip);
    	list.add(EnumChatFormatting.WHITE + "Кол-во видиокарт: " + countVideocard);
    }
	
}
