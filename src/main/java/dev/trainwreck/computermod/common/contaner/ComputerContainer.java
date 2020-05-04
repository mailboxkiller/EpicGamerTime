package dev.trainwreck.computermod.common.contaner;

import dev.trainwreck.computermod.common.tileentity.TileEntityUIBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class ComputerContainer extends ContainerBase {
    public ComputerContainer(int windowID, PlayerInventory playerInventory, TileEntityUIBase tileEntity) {
        super(windowID, playerInventory, tileEntity);
    }

    public ComputerContainer(int windowID, PlayerInventory playerInventory, PacketBuffer data) {
        this(windowID, playerInventory, getTileEntity(playerInventory,data));
    }
}
