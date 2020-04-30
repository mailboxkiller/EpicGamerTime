package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.api.redstone.RedstoneAPI;
import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.Computer;
import dev.trainwreck.computermod.computer.javascript.JavaScriptProgram;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private Computer computer = new Computer(this);
    private JavaScriptProgram test = new JavaScriptProgram(computer);

    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType());
        test.addApi(new RedstoneAPI());
        test.setProgram(
                "var side = \"left\";"+
                "if(RedstoneAPI.getOutput(side)==15){"+
                "RedstoneAPI.setOutput(side,false);" +
                "}else{"+
                "RedstoneAPI.setOutput(side,true);" +
                "}"+
                "//print(RedstoneAPI.getOutput())");
    }

    public void updateTiles(BlockPos tilePos){
        /*test.setProgram("if(RedstoneAPI.getOutput(\"back\")==15){"+
                            "RedstoneAPI.setOutput(\"back\",false);" +
                        "}else{"+
                            "RedstoneAPI.setOutput(\"back\",true);" +
                        "}"+
                        "print(RedstoneAPI.getOutput(\"back\"))");*/
        //test.runProgram();
    }

    int i = 0;
    @Override
    public void tick() {
        i++;
        if(i > 10){
            test.runProgram();
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockState().getBlock());
            i = 0;
        }
    }
    public Computer getComputer() {
        return computer;
    }

}
