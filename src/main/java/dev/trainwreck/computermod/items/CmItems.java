package dev.trainwreck.computermod.items;

import dev.trainwreck.computermod.registry.RegistrationHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.EnumSet;

public enum CmItems {
    FLOPPY_DISC(new ItemFloppyDisc()),
    PLASTIC_SHEET(new ItemBase(new Item.Properties().group(ItemGroup.MATERIALS), "plastic_sheet"));

    private ItemBase item;

    CmItems(ItemBase item) {
        this.item = item;
    }

    public static void RegisterItems(RegistrationHelper registrationHelper) {
        EnumSet.allOf(CmItems.class).forEach(currentItem -> {
            registrationHelper.RegisterItem(currentItem.item);
        });
    }

}