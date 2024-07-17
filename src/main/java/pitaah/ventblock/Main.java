package pitaah.ventblock;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pitaah.ventblock.block.BlockVent;
import pitaah.ventblock.tileEntity.TileEntityVent;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class Main implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "ventblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Main initialized.");
    }

	@Override
	public void beforeGameStart() {
		int startingBlockId = 2000;

		new BlockBuilder(MOD_ID)
			.setLuminance(14)
			.setTickOnLoad()
			.build(new BlockVent("vent", startingBlockId++, Material.grass));

		EntityHelper.Core.createTileEntity(TileEntityVent.class, "vent");
	}

	@Override
	public void afterGameStart() {}
	@Override
	public void onRecipesReady() {}
	@Override
	public void initNamespaces() {}
}
