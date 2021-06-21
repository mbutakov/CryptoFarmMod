package ru.mbutakov.auroracryptofarm.utils;

import lombok.Data;
import lombok.Getter;


public enum EnumPcTier {
	
	LOW,MIDDLE,TOP;

	
	public static EnumFormatMotherboard[] formatMotherboard(EnumPcTier tier) {

		switch (tier) {
		case LOW:
			return new EnumFormatMotherboard[]{EnumFormatMotherboard.MicroATX};
		case MIDDLE:
			return new EnumFormatMotherboard[]{EnumFormatMotherboard.MicroATX, EnumFormatMotherboard.MiniITX};

		case TOP:
			return new EnumFormatMotherboard[]{EnumFormatMotherboard.MicroATX, EnumFormatMotherboard.MiniITX, EnumFormatMotherboard.StandartATX};
		}
		return null;
	}
}
