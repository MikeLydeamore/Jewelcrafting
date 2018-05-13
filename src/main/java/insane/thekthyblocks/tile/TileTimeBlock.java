package insane.thekthyblocks.tile;

import insane.thekthyblocks.Config;
import insane.thekthyblocks.capability.EnergyStorageMachine;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

public class TileTimeBlock extends TileEntity implements ICapabilityProvider, ITickable {
	
	private EnergyStorageMachine energy = new EnergyStorageMachine(1000000, 1000000);
	private int radius = Config.baseRadius;
	private int energyRequirement = Config.energyPerBlock;
	private double energyMultiplier = 1;
	
	public void setRadius(int newRadius)
	{
		this.radius = newRadius;
	}
	
	public void incrementRadius(int amount)
	{
		radius += amount;
	}
	
	public void setEnergyRequirement(int newEnergyRequirement)
	{
		this.energyRequirement = newEnergyRequirement;
	}
	
	public void setEnergyMultiplier(double newEnergyMultiplier)
	{
		this.energyMultiplier = newEnergyMultiplier;
	}
	
	public void multipliyEnergyMultiplier(double amount)
	{
		energyMultiplier *= amount;
	}
	
	public double getEnergyMultiplier()
	{
		return (this.energyMultiplier);
	}
	
	@Override
	public void update()
	{
		if (this.world.isRemote)
			return;
		
		int energyRequired = (int) (energyRequirement*energyMultiplier);

		if (energy.extractEnergyByMachine(energyRequired, true) >= energyRequired)
		{
			BlockPos pos = this.getPos();
			
			for (int x = -1*radius; x <= radius ; x++)
			{
				for (int z = -1*radius; z <= radius; z++)
				{
					BlockPos targetPos = pos.add(x, 0, z);
					IBlockState state = world.getBlockState(targetPos);
					Block block = state.getBlock();
					
					if (block instanceof IGrowable)
					{
						IGrowable plant = (IGrowable) block;
						//Have to check twice - once to see if we should bother starting and once "per block" in the loop.
						if (plant.canGrow(world, targetPos, state, world.isRemote) && (energy.extractEnergyByMachine(energyRequired, true) >= energyRequired))
						{
							energy.extractEnergyByMachine(energyRequired, false);
							block.updateTick(world, targetPos, state, world.rand);
						}
					}
				}
			}
		}
	}

	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
			return true;
		
		return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T>  capability, EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
			return (T) energy;
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag = super.writeToNBT(tag);
		
		NBTTagInt energyTag = (NBTTagInt) CapabilityEnergy.ENERGY.getStorage().writeNBT(CapabilityEnergy.ENERGY, energy, null);
		
		tag.setTag("energy", energyTag);
		
		tag.setInteger("radius", radius);
		tag.setInteger("energyRequirement", energyRequirement);
		tag.setDouble("energyMultiplier", energyMultiplier);
		
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagInt energyTag = (NBTTagInt) tag.getTag("energy");
		if (energyTag != null)
			CapabilityEnergy.ENERGY.getStorage().readNBT(CapabilityEnergy.ENERGY, energy, null, energyTag);
		
		radius = tag.getInteger("radius");
		energyRequirement = tag.getInteger("energyRequirement");
		energyMultiplier = tag.getDouble("energyMultiplier");
	}
}
