package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.Computer;
import dev.trainwreck.computermod.computer.Test;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private Computer computer = new Computer();
    private Test test = new Test();

    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType());

    }

    public Computer getComputer() {
        return computer;
    }

    public void updateTiles(BlockPos tilePos){
        test.test();
    }


    @Nullable
    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void tick() {

    }
}
