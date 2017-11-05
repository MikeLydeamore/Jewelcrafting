package insane.jewelcrafting.sockets;

import net.minecraft.nbt.NBTTagCompound;

public class SocketUtil {
	
	public static NBTTagCompound createSocketTag(int slots)
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		for (int i = 0 ; i < slots ; i++)
		{
			tag.setString("socket-"+i, "none");
		}
		
		tag.setInteger("number", slots);
		
		return tag;
	}
	
	public static int getFirstAvailableSlotNumber(NBTTagCompound socketTag)
	{
		for (int i = 0 ; i < socketTag.getInteger("number") ; i++)
		{
			if (socketTag.getString("socket-"+i) == "none")
				return i;
		}
		
		return -1;
	}
	
	public static NBTTagCompound setSocket(NBTTagCompound socketTag, String type, int slot)
	{
		socketTag.setString("socket-"+slot, type);
		
		return socketTag;
	}

}
