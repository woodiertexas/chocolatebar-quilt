package io.github.woodiertexas.chocolatebar;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

public class ChocolateBar implements ModInitializer {
	//Making a logger
	public static final Logger LOGGER = LogManager.getLogger("ExampleMod");

	/*
    Creating items/blocks/etc and defining their properties
     */
	public static final Item QUILTY_WRAPPING_PAPER = new Item(new QuiltItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON));
	public static final Item TOOLCHAIN_WRAPPING_PAPER = new Item(new QuiltItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON));

	//This code for a food item is so big it had to be put on multiple lines.
	public static final ChocolateBarItem CHOCOLATE_BAR = new ChocolateBarItem(
			new QuiltItemSettings()
					.group(ItemGroup.FOOD).food(
							new FoodComponent.Builder()
									.hunger(1)
									.saturationModifier(0)
									.alwaysEdible()
									.snack()
									.statusEffect(
											new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600), 0.15f).build()
									)
					);

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info( "Hello Quilt world from ExampleMod v{}!", mod.metadata().version().raw() );

		/*
		Every block, item, entity, and biome must sign their registration papers.
		All registration papers must be turned into the ModInitializer in the @Override Building

		In all seriousness, the following code registers any item, block, entity, or biome added by the mod.
		 */

		Registry.register(Registry.ITEM, new Identifier("chocolatebar", "chocolate_bar"), CHOCOLATE_BAR);
		Registry.register(Registry.ITEM, new Identifier("chocolatebar", "quilty_wrapping_paper"), QUILTY_WRAPPING_PAPER);
		Registry.register(Registry.ITEM, new Identifier("chocolatebar", "toolchain_wrapping_paper"), TOOLCHAIN_WRAPPING_PAPER);

		/*
		This automatically loads up a datapack.
		In this case it's a modified chocolate bar recipe if Create is present.
		 */

		if (QuiltLoader.isModLoaded("create")); {
			ResourceLoader.registerBuiltinResourcePack(
					new Identifier("chocolatebar", "create_compat"),
					QuiltLoader.getModContainer("chocolatebar").get(),
					org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType.DEFAULT_ENABLED
			);
		}
	}
}