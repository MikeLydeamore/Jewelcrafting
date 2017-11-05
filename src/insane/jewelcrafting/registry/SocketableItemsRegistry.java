package insane.jewelcrafting.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SocketableItemsRegistry {
	
	public static void init()
	{
		SocketRecipe.addUpgradeRecipe(Items.DIAMOND_PICKAXE.getClass(), new ItemStack(Items.DIAMOND), 0);
	}

}
