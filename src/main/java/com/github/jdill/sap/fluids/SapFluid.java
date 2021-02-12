package com.github.jdill.sap.fluids;

import com.github.jdill.sap.Registry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SapFluid extends ForgeFlowingFluid {

    private static final FluidAttributes.Builder ATTRIBUTES_BUILDER = FluidAttributes.builder(
            new ResourceLocation("sap:blocks/sap_fluid_still"),
            new ResourceLocation("sap:blocks/sap_fluid_flowing"))
        .density(3000)
        .viscosity(6000);
    private static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(
            Registry.SAP_FLUID, Registry.SAP_FLUID_FLOWING, ATTRIBUTES_BUILDER)
        .bucket(Registry.SAP_BUCKET_ITEM)
        .block(Registry.SAP_FLUID_BLOCK)
        .levelDecreasePerBlock(2)
        .tickRate(20);

    protected SapFluid() {
        super(PROPERTIES);
    }

    public static class Flowing extends ForgeFlowingFluid.Flowing {
        public static final String ID = "sap_fluid_flowing";

        public Flowing() {
            super(PROPERTIES);
        }
    }

    public static class Source extends ForgeFlowingFluid.Source {
        public static final String ID = "sap_fluid_still";

        public Source() {
            super(PROPERTIES);
        }

    }
}
