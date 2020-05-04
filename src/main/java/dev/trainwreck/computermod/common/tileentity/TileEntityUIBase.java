package dev.trainwreck.computermod.common.tileentity;

import dev.trainwreck.computermod.common.blocks.BlockBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TileEntityUIBase extends LockableLootTileEntity {
    private ContainerType<?> containerType;

    public TileEntityUIBase(TileEntityType<?> tileEntityTypeIn, ContainerType<?> containerType) {
        super(tileEntityTypeIn);
        this.containerType = containerType;
    }

    @Override
    protected ITextComponent getDefaultName() {
        BlockBase block = (BlockBase)this.getBlockState().getBlock();
        return new TranslationTextComponent("contaner."+block.getInternalID());
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return containerType.create(id,player);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {

    }

    public ContainerType<?> getContainerType() {
        return containerType;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

}
