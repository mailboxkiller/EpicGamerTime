package dev.trainwreck.computermod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemFloppyDisc extends ItemBase {

    //TODO Make Item Dyeable

    public ItemFloppyDisc() {
        super(new Item.Properties().group(ItemGroup.REDSTONE), "floppy_disc");
    }

}
