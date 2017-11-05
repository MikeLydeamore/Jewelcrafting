package insane.jewelcrafting.events;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TooltipHandler {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void handleTooltipEvent(ItemTooltipEvent event)
	{
		NBTTagCompound baseTag = event.getItemStack().getTagCompound();
		if (baseTag != null &&  baseTag.hasKey("sockets"))
		{
			NBTTagCompound tag = (NBTTagCompound) event.getItemStack().getTagCompound().getTag("sockets");
			
			for (int i = 0 ; i < tag.getInteger("number") ; i++)
			{
				if (tag.getString("socket-"+i)=="none")
				{
					event.getToolTip().add("Socket "+(i+1)+": Empty");
				}
				if (tag.getString("socket-"+i)=="diamond")
				{
					event.getToolTip().add("Socket "+(i+1)+": diamond");
				}
			}
		}
	}

}
