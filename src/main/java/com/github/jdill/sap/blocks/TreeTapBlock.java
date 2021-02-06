package com.github.jdill.sap.blocks;

import com.github.jdill.sap.tileentity.TreeTapTileEntity;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.stream.Stream;

public class TreeTapBlock extends Block {

    public static final String ID = "tree_tap";
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(7, 6.6, 10, 9, 7.6, 16),
            Block.makeCuboidShape(7, 5.6, 9, 9, 6.6, 10),
            Block.makeCuboidShape(7, 7.6, 12, 9, 9.6, 14),
            Block.makeCuboidShape(6, 8.6, 12, 10, 9.6, 14),
            Block.makeCuboidShape(7, 8.6, 11, 9, 9.6, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0, 6.6, 7, 6, 7.6, 9),
            Block.makeCuboidShape(6, 5.6, 7, 7, 6.6, 9),
            Block.makeCuboidShape(2, 7.6, 7, 4, 9.6, 9),
            Block.makeCuboidShape(2, 8.6, 6, 4, 9.6, 10),
            Block.makeCuboidShape(1, 8.6, 7, 5, 9.6, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(7, 6.6, 0, 9, 7.6, 6),
            Block.makeCuboidShape(7, 5.6, 6, 9, 6.6, 7),
            Block.makeCuboidShape(7, 7.6, 2, 9, 9.6, 4),
            Block.makeCuboidShape(6, 8.6, 2, 10, 9.6, 4),
            Block.makeCuboidShape(7, 8.6, 1, 9, 9.6, 5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(10, 6.6, 7, 16, 7.6, 9),
            Block.makeCuboidShape(9, 5.6, 7, 10, 6.6, 9),
            Block.makeCuboidShape(12, 7.6, 7, 14, 9.6, 9),
            Block.makeCuboidShape(12, 8.6, 6, 14, 9.6, 10),
            Block.makeCuboidShape(11, 8.6, 7, 15, 9.6, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, SHAPE_N, Direction.EAST, SHAPE_E,
            Direction.SOUTH, SHAPE_S, Direction.WEST, SHAPE_W
    ));

    public TreeTapBlock() {
        super(Block.Properties.create(Material.WOOD)
                .notSolid()
                .hardnessAndResistance(3.5F, 4.0F)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
        );
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TreeTapTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state.get(FACING));
    }

    /**
     * Only allows the Tree Tap to be places on the solid side of blocks, and only solid blocks.
     */
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockpos = pos.offset(direction.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        return blockstate.isSolidSide(worldIn, blockpos, direction);
    }

    /**
     * Searches for a valid wall to place on when placed on top of a block.
     */
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for(Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.with(FACING, direction1);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }


    /**
     * When the block the Tree Tap is placed on is broken, also break the Tree Tap.
     */
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f;
    }
}
