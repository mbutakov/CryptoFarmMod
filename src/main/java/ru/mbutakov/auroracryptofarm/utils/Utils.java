package ru.mbutakov.auroracryptofarm.utils;

import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import ru.mbutakov.auroracryptofarm.common.items.MotherboardItem;

public class Utils {

	  public static String formatNumber(double number) {
		    String formattednumber = "0";
		    if (Double.valueOf(number) % 1 != 0) {
		      formattednumber = String.format("%.2f", new Object[] { Double.valueOf(number) });
		    }else {
		    	 formattednumber = String.format("%.0f", new Object[] { Double.valueOf(number) });
		    }
//		    } else if (number >= 1000.0D && number < 1000000.0D) {
//		      formattednumber = String.format("%.2fK", new Object[] { Double.valueOf(number / 1000.0D) });
//		    } else if (number >= 1000000.0D && number < 1.0E9D) {
//		      formattednumber = String.format("%.2fM", new Object[] { Double.valueOf(number / 1000000.0D) });
//		    } else if (number >= 1.0E9D && number < 1.0E12D) {
//		      formattednumber = String.format("%.2fG", new Object[] { Double.valueOf(number / 1.0E9D) });
//		    } else if (number >= 1.0E12D && number < 1.0E15D) {
//		      formattednumber = String.format("%.2fT", new Object[] { Double.valueOf(number / 1.0E12D) });
//		    } 
		    return formattednumber;
		  }
	  
	  
	  public static int[] getNextDetail(Container inv) {
		  int array[] = new int[5];
		  try{
			  if(inv.getSlot(2).getHasStack()) {
				  	array[1] = 1;
			  }
			  if(inv.getSlot(0).getHasStack()) {
				  array[1] = 2;
			  }
			  if(inv.getSlot(2).getStack() != null){
				  ItemStack Motherboard = inv.getSlot(2).getStack();
				  if(Motherboard.getItem() instanceof MotherboardItem) {
					  MotherboardItem motherboard = (MotherboardItem) Motherboard.getItem();
					  array[2] = motherboard.getCountVideocard();
				  }
			  }else {
				  array[2] = 0;
			  }
			   
		  }catch (Exception e) {
			// TODO: handle exception
		}

		  
		  
		  
		  return array ;
	  }
	  
}
