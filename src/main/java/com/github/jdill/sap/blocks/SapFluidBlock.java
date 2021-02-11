package com.github.jdill.sap.blocks;

import com.github.jdill.sap.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;

public class SapFluidBlock extends FlowingFluidBlock {

    public static final String ID = "sap_fluid_block";

    public SapFluidBlock() {
        super(Registry.SAP_FLUID,
            Block.Properties.create(Material.WATER)
                .doesNotBlockMovement()
                .hardnessAndResistance(100.0F)
                .noDrops()
        );
    }
}
