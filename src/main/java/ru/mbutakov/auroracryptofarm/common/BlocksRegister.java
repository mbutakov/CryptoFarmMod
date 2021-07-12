package ru.mbutakov.auroracryptofarm.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.blocks.bank.BlockBank;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.BlockPc;
import ru.mbutakov.auroracryptofarm.utils.EnumPcTier;

public class BlocksRegister {
	
    public static Block BlockPcLow,BlockPcMiddle,BlockPcTop;
    public static Block BlockBank;
    
	public static void registerBlocks() {
		BlockPcLow = new BlockPc("Block Pc Low","block_pc",Main.cryptoTab, EnumPcTier.LOW);
        BlockPcMiddle = new BlockPc("Block Pc Middle","block_pc",Main.cryptoTab, EnumPcTier.MIDDLE);
        BlockPcTop = new BlockPc("Block Pc Top","block_pc",Main.cryptoTab, EnumPcTier.TOP);
        BlockBank = new BlockBank("Block_Bank","banktexture",Main.cryptoTab);
	}

}
