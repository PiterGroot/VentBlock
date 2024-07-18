package pitaah.ventblock.block;

import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import pitaah.ventblock.tileEntity.TileEntityVent;

public class BlockVent extends BlockTileEntity {
	public BlockVent(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityVent();
	}
}
