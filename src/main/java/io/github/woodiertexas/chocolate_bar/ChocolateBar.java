package io.github.woodiertexas.chocolate_bar;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

public class ChocolateBar implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Chocolate Bar");
	public static final String MODID = "chocolate_bar";

	public static Block CHOCOLATE;
	public static FlowableFluid STILL_CHOCOLATE;
	public static FlowableFluid FLOWING_CHOCOLATE;
	public static Item CHOCOLATE_BUCKET;

    // Creating items/blocks/etc and defining their properties
	public static final ChocolateBarItem CHOCOLATE_BAR = new ChocolateBarItem(
			new QuiltItemSettings().food(
					new FoodComponent.Builder()
							.hunger(1)
							.saturationModifier(0)
							.alwaysEdible()
							.snack()
							.statusEffect(
									new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 1), 0.15f).build()
			)
	);

	public static final ChocolateBarItem CHOCOLATE_BAR_ZOOMIES = new ChocolateBarItem(
			new QuiltItemSettings().food(
					new FoodComponent.Builder()
							.hunger(1)
							.saturationModifier(0)
							.alwaysEdible()
							.snack()
							.statusEffect(
									new StatusEffectInstance(StatusEffects.SPEED, 600, 4), 0.15f).build()
			)
	);

    @Override
    public void onInitialize(ModContainer mod) {
		/*
		Every block, item, entity, and biome must sign their registration papers.
		All registration papers must be turned into the ModInitializer in the @Override Building

		In all seriousness, the following code registers any item, block, entity, or biome added by the mod.
		 */
		//Registry.register(Registries.ITEM, new Identifier("chocolate_bar", "quilty_wrapping_paper"), QUILTY_WRAPPING_PAPER);
		//Registry.register(Registries.ITEM, new Identifier("chocolate_bar", "toolchain_wrapping_paper"), TOOLCHAIN_WRAPPING_PAPER);

        Registry.register(Registries.ITEM, new Identifier(MODID, "chocolate_bar"), CHOCOLATE_BAR);
		Registry.register(Registries.ITEM, new Identifier(MODID, "chocolate_bar_zoomies"), CHOCOLATE_BAR_ZOOMIES);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(entries -> entries.addItem(CHOCOLATE_BAR));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(entries -> entries.addItem(CHOCOLATE_BAR_ZOOMIES));

        // Registers the recipe for Chocolate Bar
        RecipeManagerHelper.registerStaticRecipe(
                VanillaRecipeBuilders.shapedRecipe("CMS")
                        .output(new ItemStack(CHOCOLATE_BAR))
                        .ingredient('C', Items.COCOA_BEANS)
                        .ingredient('M', Items.MILK_BUCKET)
                        .ingredient('S', Items.SUGAR)
                        .build(new Identifier(MODID, "chocolate_bar"), "")
        );

		RecipeManagerHelper.registerStaticRecipe(
			VanillaRecipeBuilders.shapedRecipe(
				"CMS", " G ")
				.output(new ItemStack(CHOCOLATE_BAR))
				.ingredient('C', Items.COCOA_BEANS)
				.ingredient('M', Items.MILK_BUCKET)
				.ingredient('S', Items.SUGAR)
				.ingredient('G', Items.GLOW_BERRIES)
				.build(new Identifier(MODID, "chocolate_bar_zoomies"), "")
		);
        LOGGER.info("Recipes are done generating. Have fun with Chocolate Bar! :)");
    }
}
