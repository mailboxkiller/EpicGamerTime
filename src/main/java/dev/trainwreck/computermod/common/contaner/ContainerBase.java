package dev.trainwreck.computermod.common.contaner;

import dev.trainwreck.computermod.common.registry.RegistrationHelper;
import dev.trainwreck.computermod.common.tileentity.TileEntityBase;
import dev.trainwreck.computermod.common.tileentity.TileEntityUIBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Objects;

public class ContainerBase extends Container {

    private TileEntityUIBase tileEntity;
    private IWorldPosCallable canInteractWithCallable;
    private boolean hasPlayerInventory = false;

    public ContainerBase(final int windowID, final PlayerInventory playerInventory, final TileEntityUIBase tileEntity){
        super(RegistrationHelper.getContainerType(), windowID);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(),tileEntity.getPos());
    }

    public ContainerBase(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowID, playerInventory, getTileEntity(playerInventory,data));
    }

    protected static TileEntityUIBase getTileEntity(@Nonnull final PlayerInventory playerInventory, @Nonnull final PacketBuffer data){
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof TileEntityUIBase){
            return (TileEntityUIBase) tileAtPos;
        }else {
            throw new IllegalStateException("TileEntity container error");
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable,playerIn,tileEntity.getBlockState().getBlock());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        if(!hasPlayerInventory)return super.transferStackInSlot(playerIn,index);

        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if(index < 36){
                if(!this.mergeItemStack(itemStack1,36,this.inventorySlots.size(),true)){
                    return ItemStack.EMPTY;
                }
            }else if(!this.mergeItemStack(itemStack1,0,36,false)){
                return ItemStack.EMPTY;
            }
            if(itemStack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
























}
