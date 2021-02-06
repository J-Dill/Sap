package com.github.jdill.sap.tileentity;

import com.github.jdill.sap.Registry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TreeTapTileEntity extends TileEntity {

    public static final String ID = "tree_tap_te";

    public TreeTapTileEntity() {
        super(Registry.TREE_TAP_TILE_ENTITY.get());
    }
}
