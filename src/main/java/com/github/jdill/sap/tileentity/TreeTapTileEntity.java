package com.github.jdill.sap.tileentity;

import com.github.jdill.sap.Registry;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeTapTileEntity extends TileEntity implements ITickableTileEntity {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final String ID = "tree_tap_te";

    private int tickCount = 0;

    public TreeTapTileEntity() {
        super(Registry.TREE_TAP_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        tickCount++;
        World worldIn = getWorld();
        if (tickCount == 20 && worldIn != null) {
            Direction direction = getBlockState().get(FACING);
            BlockPos blockpos = pos.offset(direction.getOpposite());
            BlockState blockstate = worldIn.getBlockState(blockpos);
            boolean isLog = blockstate.getBlock().getTags().contains(new ResourceLocation("minecraft:logs"));
            if (isLog) {
                Item item = Registry.SAP_ITEM.get();
                ItemStack stack = item.getDefaultInstance();
                ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
                worldIn.addEntity(itemEntity);
            }
            tickCount = 0;
        }
    }
}
