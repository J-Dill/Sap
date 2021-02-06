package com.github.jdill.sap.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class TreeTapBlock extends Block {

    public static final String ID = "tree_tap";

    public TreeTapBlock() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.5F, 4.0F)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
        );
    }
}
