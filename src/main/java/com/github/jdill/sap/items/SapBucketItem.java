package com.github.jdill.sap.items;

import com.github.jdill.sap.Registry;
import javax.annotation.Nullable;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class SapBucketItem extends BucketItem {

    public static final String ID = "sap_bucket_item";

    public SapBucketItem() {
        super(
                Registry.SAP_FLUID,
                new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)
        );
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new FluidBucketWrapper(stack);
    }
}
