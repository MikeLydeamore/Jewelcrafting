package insane.thekthyblocks.capability;

import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageMachine extends EnergyStorage {

	public EnergyStorageMachine(int capacity) {
		super(capacity);
	}

	public EnergyStorageMachine(int capacity, int maxReceive)
	{
		//Machines can't have energy extracted, that would be cheating.
		super(capacity, maxReceive, 0);
	}

	//Use this to use energy by the machine.
	public int extractEnergyByMachine(int maxExtract, boolean simulate)
	{
		int energyExtracted = Math.min(energy, maxExtract);
		if (!simulate)
			energy -= energyExtracted;
		return energyExtracted;
	}
}
