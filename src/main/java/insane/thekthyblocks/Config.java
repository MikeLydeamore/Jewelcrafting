package insane.thekthyblocks;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static int energyPerBlock;
	public static int baseRadius;
	
	public static void doConfig(File configFile)
	{
		Configuration config = new Configuration(configFile);
		
		energyPerBlock = config.getInt("energyPerBlock", "Settings", 1000, 0, Integer.MAX_VALUE, "Energy (RF) per block");
		baseRadius = config.getInt("Radius", "Settings", 1, 1, 64, "Radius of the block (base)");
	}

}
