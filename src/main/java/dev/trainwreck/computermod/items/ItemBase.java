package dev.trainwreck.computermod.items;

import net.minecraft.item.Item;

public class ItemBase extends Item {

    private String internalName;

    public ItemBase(Properties properties, String internalName) {
        super(properties);
        this.internalName = internalName;
    }

    public String getInternalName() {
        return internalName;
    }
}
