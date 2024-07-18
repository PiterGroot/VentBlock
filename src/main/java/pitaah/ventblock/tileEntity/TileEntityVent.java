package pitaah.ventblock.tileEntity;

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
	public boolean activated = true;

	@Override
	public void tick()
	{
		if(!activated)
			return;

		worldObj.spawnParticle("explode",this.x + .5f, this.y, this.z+ .5f, 0, .75f, 0);
		List<Entity> list = this.worldObj.getEntitiesWithinAABB(Entity.class, AABB.getBoundingBoxFromPool(this.x, this.y + 1, this.z, this.x + 1, this.y + 9, this.z+ 1));

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				Entity e = (Entity) iterator.next();
				if (e instanceof EntityPlayer || e instanceof EntityAnimal || e instanceof EntityMonster)
				{

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
}
