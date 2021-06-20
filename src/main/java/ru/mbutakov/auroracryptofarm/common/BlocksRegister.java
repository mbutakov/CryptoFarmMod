package ru.mbutakov.auroracryptofarm.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.common.blocks.pc.BlockPc;

public class BlocksRegister {
	
    public static Block BlockPc;
    
	public static void registerBlocks() {
        BlockPc = new BlockPc("Block Pc","block_pc",Main.cryptoTab);
	}

}
