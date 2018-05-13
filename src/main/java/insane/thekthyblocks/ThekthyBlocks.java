package insane.thekthyblocks;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=ThekthyBlocks.MODID, version="0.0.1", name="Thekthy Blocks")
public class ThekthyBlocks {
	
	public static final String MODID = "thekthyblocks";
	
	@Instance(MODID)
	public static ThekthyBlocks instance;
	
	@SidedProxy(clientSide="insane.thekthyblocks.ClientProxy", serverSide="insane.thekthyblocks.CommonProxy")
	public static CommonProxy proxy;
	
	
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		Config.doConfig(event.getSuggestedConfigurationFile());
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
	}
	

	
	
}
