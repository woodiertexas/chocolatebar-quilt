package io.github.woodiertexas.chocolate_bar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import static io.github.woodiertexas.chocolate_bar.ChocolateBar.CHOCOLATE;

public abstract class ChocolateFluid extends FlowableFluid {
	@Override
	public Fluid getFlowing() {
		return ChocolateBar.FLOWING_CHOCOLATE;
	}

	@Override
	public Fluid getStill() {
		return ChocolateBar.STILL_CHOCOLATE;
	}

	@Override
	public Item getBucketItem() {
		return ChocolateBar.CHOCOLATE_BUCKET;
	}

	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == getStill() || fluid == getFlowing();
	}

	@Override
	protected boolean isInfinite(World world) {
		return false;
	}

	@Override
	protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
		final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
	}

	@Override
	protected int getFlowSpeed(WorldView world) {
		return 3;
	}

	@Override
	protected int getLevelDecreasePerBlock(WorldView world) {
		return 2;
	}

	@Override
	protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
		return false;
	}

	@Override
	public int getTickRate(WorldView world) {
		return 5;
	}

	@Override
	protected float getBlastResistance() {
		return 100f;
	}

	@Override
	public int getLevel(FluidState state) {
		return 8;
	}

	public BlockState toBlockState(FluidState state) {
		return CHOCOLATE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
	}

	public static class Flowing extends ChocolateFluid {
		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}

		public boolean isSource(FluidState fluidState) {
			return false;
		}
	}

	public static class Still extends ChocolateFluid {
		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState fluidState) {
			return true;
		}
	}
}
