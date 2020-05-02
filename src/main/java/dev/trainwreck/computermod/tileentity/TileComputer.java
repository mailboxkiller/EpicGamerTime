package dev.trainwreck.computermod.tileentity;

import de.esoco.coroutine.Coroutine;
import de.esoco.coroutine.CoroutineScope;
import de.esoco.coroutine.step.Loop;
import de.esoco.lib.datatype.Range;
import dev.trainwreck.computermod.api.redstone.RedstoneAPI;
import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.Computer;
import dev.trainwreck.computermod.computer.javascript.JavaScriptProgram;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;

import static de.esoco.coroutine.step.CodeExecution.run;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private Computer computer = new Computer(this);


    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType());
        computer.getProgram().addApi(new RedstoneAPI());
        computer.getProgram().setProgram(
                "setTimeout(500);"+
                "var side = \"front\";"+
                "if(RedstoneAPI.getOutput(side)==15){"+
                "RedstoneAPI.setOutput(side,false);" +
                "}else{"+
                "RedstoneAPI.setOutput(side,true);" +
                "}");
    }

    public void updateTiles(BlockPos tilePos){

    }

    @Override
    public void tick() {
        if(computer.isDirty()){
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockState().getBlock());
        }
    }
    public Computer getComputer() {
        return computer;
    }

}
