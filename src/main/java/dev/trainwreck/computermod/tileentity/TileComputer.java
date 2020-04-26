package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.Computer;
import dev.trainwreck.computermod.computer.apis.RedstoneAPI;
import dev.trainwreck.computermod.computer.javascript.JavaScriptMachine;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private Computer computer = new Computer();
    private JavaScriptMachine javaScriptMachine = new JavaScriptMachine(computer);

    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType());
        javaScriptMachine.addAPI(new RedstoneAPI());

    }

    public void updateTiles(BlockPos tilePos){

        javaScriptMachine.test("print(RedstoneAPI.getSides())");
        //computer.startComputer();
    }

    @Override
    public void tick() {

    }
}
