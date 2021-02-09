package com.github.jdill.sap.fluids;

import com.github.jdill.sap.Registry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SapFluid extends ForgeFlowingFluid {

    private static final FluidAttributes.Builder attributesBuilder = FluidAttributes.builder(
            new ResourceLocation("minecraft", "block/water_still"),
            new ResourceLocation("minecraft", "block/water_flow")
    ).density(1024).viscosity(1024);
    private static final ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(
            Registry.SAP_FLUID, Registry.SAP_FLUID_FLOWING, attributesBuilder
    ).bucket(Registry.SAP_BUCKET_ITEM);

    protected SapFluid() {
        super(properties);
    }

    public static class Flowing extends ForgeFlowingFluid.Flowing {
        public static final String ID = "sap_fluid_flowing";

        public Flowing() {
            super(properties);
        }
    }

    public static class Source extends ForgeFlowingFluid.Source {
        public static final String ID = "sap_fluid";

        public Source() {
            super(properties);
        }

    }
}
