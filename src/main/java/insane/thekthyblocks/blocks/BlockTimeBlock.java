package insane.thekthyblocks.blocks;

import insane.thekthyblocks.items.ItemRadiusUpgrade;
import insane.thekthyblocks.tile.TileTimeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTimeBlock extends Block implements ITileEntityProvider {

	public BlockTimeBlock() 
	{
		super(Material.ROCK);
		this.setUnlocalizedName("timeBlock");
		this.setRegistryName("timeBlock");
		this.setHardness(2);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTimeBlock();
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack heldItem = playerIn.getHeldItem(hand);
		if (heldItem != null && heldItem.getItem() instanceof ItemRadiusUpgrade)
		{
			TileTimeBlock te = (TileTimeBlock) worldIn.getTileEntity(pos);
			
			te.incrementRadius(1);
			te.multipliyEnergyMultiplier(1.5);
			te.markDirty();
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

}
