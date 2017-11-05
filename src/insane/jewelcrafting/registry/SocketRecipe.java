package insane.jewelcrafting.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SocketRecipe {
	
	public final Class<? extends Item> input;
	public final ItemStack modifier;
	public final int cost;

	
	public SocketRecipe(Class<? extends Item> input, ItemStack modifier, int cost)
	{
		this.input = input;
		this.modifier = modifier;
		this.cost = cost;
	}
	
	private static List<SocketRecipe> recipes = new ArrayList<SocketRecipe>();
	
	public static void addUpgradeRecipe(Class<? extends Item> input, ItemStack modifier, int cost)
	{
		recipes.add(new SocketRecipe(input, modifier, cost));
	}
	
	public static boolean isValidInput(ItemStack input, ItemStack modifier)
	{
		if (input == null || modifier == null || modifier.getItem() != Items.DIAMOND)
			return false;
		
		for (SocketRecipe recipe : recipes)
		{
			if (recipe.input.isAssignableFrom(input.getItem().getClass()))
			{
				NBTTagCompound tag = input.getTagCompound();
				if (tag == null || !tag.hasKey("sockets"))
					return false;
				
				NBTTagCompound socketTag = (NBTTagCompound) tag.getTag("sockets");
				int availableSockets = 0;
				for (int i = 0 ; i < socketTag.getInteger("number"); i++)
				{
					if (socketTag.getString("socket-"+i) == "none")
						availableSockets++;
				}
				
				if (availableSockets > 0)
					return true;
			}
		}
		
		return false;
	}
}
