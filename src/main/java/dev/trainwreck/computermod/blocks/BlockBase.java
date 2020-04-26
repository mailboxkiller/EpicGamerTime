package dev.trainwreck.computermod.blocks;

import dev.trainwreck.computermod.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockBase extends Block {
    private String InternalID;
    private ItemGroup itemGroup = ItemGroup.MISC;

    public BlockBase(Properties properties, String internalID) {
        super(properties);
        setInternalID(internalID);

    }

    public void setInternalID(String internalID) {
        InternalID = internalID;
    }

    public String getInternalID() {
        return InternalID;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

}
