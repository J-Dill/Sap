package com.github.jdill.sap.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class SapItem extends Item {

    public static final String ID = "sap";

    public SapItem() {
        super(new Item.Properties().group(ItemGroup.MISC));
    }

}
