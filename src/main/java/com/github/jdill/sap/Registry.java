package com.github.jdill.sap;

import com.github.jdill.sap.blocks.TreeTapBlock;
import com.github.jdill.sap.fluids.SapFluid;
import com.github.jdill.sap.items.SapBucketItem;
import com.github.jdill.sap.items.SapItem;
import com.github.jdill.sap.tileentity.TreeTapTileEntity;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Sap.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Sap.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Sap.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Sap.MODID);

    //===============
    // Blocks
    //===============
    public static final RegistryObject<Block> TREE_TAP_BLOCK = BLOCKS.register(TreeTapBlock.ID, TreeTapBlock::new);

    //===============
    // Tile Entities
    //===============
    public static final RegistryObject<TileEntityType<TreeTapTileEntity>> TREE_TAP_TILE_ENTITY = TILE_ENTITIES.register(
            TreeTapTileEntity.ID,
            () -> TileEntityType.Builder.create(TreeTapTileEntity::new, TREE_TAP_BLOCK.get())
                    .build(null)
    );

    //===============
    // Items
    //===============
    public static final RegistryObject<Item> TREE_TAP_ITEM = ITEMS.register(TreeTapBlock.ID,
            () -> new BlockItem(TREE_TAP_BLOCK.get(), new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> SAP_ITEM = ITEMS.register(SapItem.ID, SapItem::new);
    public static final RegistryObject<Item> SAP_BUCKET_ITEM = ITEMS.register(SapBucketItem.ID, SapBucketItem::new);

    //===============
    // Fluids
    //===============
    public static final RegistryObject<Fluid> SAP_FLUID = FLUIDS.register(SapFluid.ID, SapFluid::new);
}
