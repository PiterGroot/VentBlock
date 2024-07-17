package pitaah.ventblock.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import pitaah.ventblock.tileEntity.TileEntityVent;

public class BlockVent extends BlockTileEntity
{
	public BlockVent(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityVent();
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		if (!world.isClientSide) {
			if (blockId > 0 && Block.getBlock(blockId).canProvidePower()) {
				int meta = world.getBlockMetadata(x, y, z);
				//boolean isOpened = isTrapdoorOpen(meta);
				boolean isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);

				if(isPowered)
					world.playSoundEffect((EntityPlayer)null, 1003, x, y, z, 0);
			}
		}
	}
}
