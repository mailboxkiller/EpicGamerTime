package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.Computer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private Computer computer = new Computer();

    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType());

    }

    public void updateTiles(BlockPos tilePos){

        computer.startComputer();
    }

    @Override
    public void tick() {

    }
}
