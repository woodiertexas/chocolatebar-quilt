package io.github.woodiertexas.chocolate_bar.client;

import io.github.woodiertexas.chocolate_bar.ChocolateBar;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class ChocolateBarClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		FluidRenderHandlerRegistry.INSTANCE.register(ChocolateBar.STILL_CHOCOLATE, ChocolateBar.FLOWING_CHOCOLATE, new SimpleFluidRenderHandler(
				new Identifier("minecraft:block/water_still"),
				new Identifier("minecraft:block/water_flow"),
				0xff0000
		));

		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), ChocolateBar.STILL_CHOCOLATE, ChocolateBar.FLOWING_CHOCOLATE);
	}
}
