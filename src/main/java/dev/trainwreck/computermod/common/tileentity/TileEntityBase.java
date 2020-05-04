package dev.trainwreck.computermod.common.tileentity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityBase extends TileEntity {

    public TileEntityBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

}
