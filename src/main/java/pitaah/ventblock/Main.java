package pitaah.ventblock;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pitaah.ventblock.block.BlockVent;
import pitaah.ventblock.tileEntity.TileEntityVent;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.util.Properties;


public class Main implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "ventblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	static BlockBuilder vent = new BlockBuilder(MOD_ID)
		.setLuminance(2)
		.setHardness(3.5f)
		.addTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final TomlConfigHandler config;
	static {
		Toml toml = new Toml("Vent Block Config\nView source code: https://github.com/PiterGroot/VentBlock");

		toml.addCategory("IDs")
			.addEntry("ventBlockID", 2330);

		config = new TomlConfigHandler(null, MOD_ID, toml);
	}

	public static final Block ventBlock = vent
		.setSideTextures("vent_side.png")
		.setTopTexture("vent_up.png")
		.setBottomTexture("vent_down.png")
		.build(new BlockVent("vent", config.getInt("IDs.ventBlockID"), Material.grass));

    @Override
    public void onInitialize() {
        LOGGER.info("VentBlock initialized.");
    }

	@Override
	public void beforeGameStart()
	{
		EntityHelper.Core.createTileEntity(TileEntityVent.class, "vent");
	}

	@Override
	public void initNamespaces()
	{
		RecipeBuilder.initNameSpace(MOD_ID);
	}

	@Override
	public void afterGameStart() {}
	@Override
	public void onRecipesReady() {
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("LML", "CRC", "CCC")
				.addInput('L', Block.logOak)
				.addInput('M', Block.mesh)
				.addInput('C', Block.cobbleStone)
				.addInput('R', Block.blockRedstone)
			.create("ventRecipe", ventBlock.getDefaultStack());
	}
}
