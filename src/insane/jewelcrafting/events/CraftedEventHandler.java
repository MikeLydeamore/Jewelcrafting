package insane.jewelcrafting.events;

import java.util.Random;

import insane.jewelcrafting.sockets.SocketUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftedEventHandler {
	
	private static Random random = new Random();
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event)
	{
		if (ItemStack.areItemsEqualIgnoreDurability(event.crafting, new ItemStack(Items.DIAMOND_PICKAXE)))
		{
			NBTTagCompound tag = event.crafting.getTagCompound();
			if (tag == null)
			{
				tag = new NBTTagCompound();
			}
			
			NBTTagCompound tagSockets = SocketUtil.createSocketTag(random.nextInt(3)+1);
			
			tag.setTag("sockets", tagSockets);
			
			event.crafting.setTagCompound(tag);
		}
	}

}
