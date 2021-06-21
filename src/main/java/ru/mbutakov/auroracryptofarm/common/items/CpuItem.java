package ru.mbutakov.auroracryptofarm.common.items;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
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
import ru.mbutakov.auroracryptofarm.utils.Utils;
@Getter
public class CpuItem extends Item {

	private double processorX;
	private double chip;
	
	public CpuItem(String name,String nameTexture,CreativeTabs tab,double processorX,int chip,int maxDamage) {
		super();
		setCreativeTab(tab);
		setTextureName(Main.MODID + ":" + nameTexture);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		setMaxDamage(maxDamage);
		this.chip = chip;
		GameRegistry.registerItem(this,name);
		this.processorX = processorX;
		if(FMLClientHandler.instance().getSide().isClient()) {
			LanguageRegistry.addName(this, name);
		}
	}
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
    	list.add(EnumChatFormatting.WHITE + "Чипсет: " +  chip);
    	double breakProcent = stack.getItemDamage() / (double)stack.getMaxDamage();
    	list.add(EnumChatFormatting.WHITE + "Сломано " + Utils.formatNumber((breakProcent*100)) + "%");
    	list.add(EnumChatFormatting.WHITE + "Коэффициент производительности: " + Utils.formatNumber(getCofProcess(stack)));
    }
    
    public double getCofProcess(ItemStack stack) {
    	double breakProcent = stack.getItemDamage() / (double)stack.getMaxDamage();
		return processorX-breakProcent;
    	
    }

}
