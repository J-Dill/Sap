package com.github.jdill.sap;

import com.github.jdill.sap.blocks.SapFluidBlock;
import com.github.jdill.sap.blocks.TreeTapBlock;
import com.github.jdill.sap.entity.projectile.SapEntity;
import com.github.jdill.sap.fluids.SapFluid;
import com.github.jdill.sap.items.SapBucketItem;
import com.github.jdill.sap.items.SapItem;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Sap.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Sap.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Sap.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Sap.MODID);

    //===============
    // Blocks
    //===============
    public static final RegistryObject<Block> TREE_TAP_BLOCK = BLOCKS.register(TreeTapBlock.ID, TreeTapBlock::new);
    public static final RegistryObject<FlowingFluidBlock> SAP_FLUID_BLOCK = BLOCKS.register(
        SapFluidBlock.ID, SapFluidBlock::new);

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
    public static final RegistryObject<ForgeFlowingFluid> SAP_FLUID = FLUIDS.register(SapFluid.Source.ID, SapFluid.Source::new);
    public static final RegistryObject<ForgeFlowingFluid> SAP_FLUID_FLOWING = FLUIDS.register(SapFluid.Flowing.ID, SapFluid.Flowing::new);

    //===============
    // Entities
    //===============
    public static final RegistryObject<EntityType<SapEntity>> SAP_ENTITY = ENTITIES.register(SapEntity.ID,
        () -> EntityType.Builder.<SapEntity>create(SapEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F)
            .trackingRange(4)
            .setShouldReceiveVelocityUpdates(true)
            .build(SapEntity.ID));
}
