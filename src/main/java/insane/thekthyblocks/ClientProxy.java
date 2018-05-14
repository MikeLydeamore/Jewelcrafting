package insane.thekthyblocks;

import insane.thekthyblocks.blocks.ModBlocks;
import insane.thekthyblocks.items.ModItems;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initModels()
	{
		ModBlocks.blockTimeBlock.initModel();
		ModItems.radiusUpgrade.initModel();
		ModItems.barryIcon.initModel();
	}
}
