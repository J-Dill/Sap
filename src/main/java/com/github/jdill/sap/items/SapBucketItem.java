package com.github.jdill.sap.items;

import com.github.jdill.sap.Registry;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

public class SapBucketItem extends BucketItem {

  public static final String ID = "sap_bucket_item";

  public SapBucketItem() {
      super(
          Registry.SAP_FLUID,
          new Item.Properties().containerItem(Items.BUCKET).group(ItemGroup.MISC));
  }
}
