package com.github.jdill.sap.fluids;

import com.github.jdill.sap.Registry;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SapFluid extends ForgeFlowingFluid {

    protected SapFluid() {
        super(new ForgeFlowingFluid.Properties(
            Registry.SAP_FLUID, Registry.SAP_FLUID_FLOWING,
            FluidAttributes.builder(
                new ResourceLocation("block/water_still"),
                new ResourceLocation("block/water_flow")
            )
        ).bucket(Registry.SAP_BUCKET_ITEM).block(Registry.SAP_FLUID_BLOCK));
    }

    public static class Flowing extends SapFluid {
        public static final String ID = "sap_fluid_flowing";

        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL_1_8);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends SapFluid {
        public static final String ID = "sap_fluid";

        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
