package insane.jewelcrafting.events;

import insane.jewelcrafting.registry.SocketRecipe;
import insane.jewelcrafting.sockets.SocketUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnvilEventHandler {
	
	@SubscribeEvent
	public void handleAnvilEvent(AnvilUpdateEvent event)
	{
		if (SocketRecipe.isValidInput(event.getLeft(), event.getRight()))
		{
			ItemStack output = event.getLeft().copy();
			NBTTagCompound socketTag = (NBTTagCompound) output.getTagCompound().getTag("sockets");
			
			SocketUtil.setSocket(socketTag, "diamond", SocketUtil.getFirstAvailableSlotNumber(socketTag));
			
			output.getTagCompound().setTag("sockets", socketTag);
			
			event.setOutput(output);
			event.setCost(0);
		}
	}
}
