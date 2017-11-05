package insane.jewelcrafting;

import insane.jewelcrafting.events.AnvilEventHandler;
import insane.jewelcrafting.events.CraftedEventHandler;
import insane.jewelcrafting.events.TooltipHandler;
import insane.jewelcrafting.registry.SocketableItemsRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Jewelcrafting.MODID, version="0.0.1", name="Jewelcrafting")
public class Jewelcrafting {

	public static final String MODID = "jewelcrafting";
	
	@Instance(MODID)
	public static Jewelcrafting instance;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CraftedEventHandler());
		MinecraftForge.EVENT_BUS.register(new TooltipHandler());
		MinecraftForge.EVENT_BUS.register(new AnvilEventHandler());
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		SocketableItemsRegistry.init();
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
}
