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
import scala.language;
@Getter
public class VideoCard extends Item {
	
	private double coinAdd;
	public VideoCard(String name,String nameTexture,CreativeTabs tab,double coinAdd,int maxDamage) {
		super();
		setCreativeTab(tab);
		setTextureName(Main.MODID + ":" + nameTexture);
		setUnlocalizedName(name);
		setMaxStackSize(1);
		GameRegistry.registerItem(this,name);
		this.coinAdd = coinAdd;
		this.setMaxDamage(maxDamage);
		if(FMLClientHandler.instance().getSide().isClient()) {
			LanguageRegistry.addName(this, name);
		}
	}


	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
    	list.add(EnumChatFormatting.WHITE + "Производительность: " + coinAdd + " монета");
    }
	
}