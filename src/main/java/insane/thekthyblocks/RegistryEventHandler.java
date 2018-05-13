package insane.thekthyblocks;

import insane.thekthyblocks.blocks.ModBlocks;
import insane.thekthyblocks.items.ItemRadiusUpgrade;
import insane.thekthyblocks.tile.TileTimeBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = ThekthyBlocks.MODID)
public class RegistryEventHandler {
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		System.out.println("registering");
		event.getRegistry().registerAll(ModBlocks.blockTimeBlock);
		GameRegistry.registerTileEntity(TileTimeBlock.class, "timeBlock");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTimeBlock).setRegistryName(ModBlocks.blockTimeBlock.getRegistryName()));
		event.getRegistry().register(new ItemRadiusUpgrade());
	}
	
	@SubscribeEvent
	public static void registerAllModels(final ModelRegistryEvent event)
	{
		ModBlocks.blockTimeBlock.initModel();
	}

}
