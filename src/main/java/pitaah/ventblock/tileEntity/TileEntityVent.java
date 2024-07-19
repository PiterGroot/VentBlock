package pitaah.ventblock.tileEntity;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.animal.EntityAnimal;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.phys.AABB;

import java.util.Iterator;
import java.util.List;

public class TileEntityVent extends TileEntity
{
	public int maxRange = 9;

	@Override
	public void tick()
	{
		worldObj.spawnParticle("explode",this.x + .5f, this.y, this.z+ .5f, 0, .75f, 0);
		int ventRange = GetVentRange();

		AABB hitBox = AABB.getBoundingBoxFromPool(this.x, this.y + 1, this.z, this.x + 1, this.y + ventRange, this.z+ 1);
		List<Entity> list = this.worldObj.getEntitiesWithinAABB(Entity.class, hitBox);

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				Entity e = (Entity) iterator.next();
				if (e instanceof EntityPlayer || e instanceof EntityAnimal || e instanceof EntityMonster)
				{
					e.fallDistance = 0;

					if (e instanceof EntityPlayer && e.isSneaking())
						return;

					e.yd += .1f;
				}

				if(e instanceof EntityItem)
				{
					e.yd += .05f;
				}
			}
		}
	}

	private int GetVentRange()
	{
		for (int i = 0; i < maxRange; i++) {
			Block block = worldObj.getBlock(this.x, this.y + 1 + i, this.z);
			if(block == null || block == Block.signWallPlanksOak
				|| block == Block.fluidWaterFlowing || block == Block.fluidWaterStill
				|| block == Block.fluidLavaFlowing || block == Block.fluidLavaStill)
				continue;

			else return i;
		}
		return maxRange;
	}
}
