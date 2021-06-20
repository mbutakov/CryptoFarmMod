package ru.mbutakov.auroracryptofarm.client;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.WorldEvent;

public class EnergyTileEvent extends WorldEvent {
  public final IEnergyTile energyTile;
  
  public EnergyTileEvent(IEnergyTile energyTile1) {
    super(((TileEntity)energyTile1).getWorldObj());
    if (this.world == null)
      throw new NullPointerException("world is null"); 
    this.energyTile = energyTile1;
  }
}
