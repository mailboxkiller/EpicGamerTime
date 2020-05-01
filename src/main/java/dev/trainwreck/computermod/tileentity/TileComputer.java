package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.api.javascript.BaseJavaScriptAPI;
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
        test.addApi(new BaseJavaScriptAPI(computer));
        test.setProgram(
                "var side = \"front\";"+
                "if(RedstoneAPI.getOutput(side)==15){"+
                "RedstoneAPI.setOutput(side,false);" +
                "}else{"+
                "RedstoneAPI.setOutput(side,true);" +
                "}"+
                "//print(RedstoneAPI.getOutput())");
    }

    public void updateTiles(BlockPos tilePos){

    }

    @Override
    public void tick() {
        test.runProgram();
        if(computer.isDirty()){
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockState().getBlock());
        }
    }
    public Computer getComputer() {
        return computer;
    }

}
