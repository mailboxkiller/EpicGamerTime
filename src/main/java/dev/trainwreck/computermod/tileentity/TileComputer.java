package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.api.redstone.RedstoneAPI;
import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.Computer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private volatile Computer computer = new Computer(this);


    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType());
        computer.getProgram().addApi(new RedstoneAPI());
/*        computer.getProgram().setProgram(
                "var side = \"front\";"+
                "if(RedstoneAPI.getOutput(side)==15){"+
                "   RedstoneAPI.setOutput(side,false);" +
                "}else{"+
                "   RedstoneAPI.setOutput(side,true);" +
                "}" +
                "setTimeout(500);" );*/
        computer.getProgram().setProgram(
                "if(RedstoneAPI.getInput(\"left\")){" +
                "   RedstoneAPI.setOutput(\"right\",false);" +
                "}else{" +
                "   RedstoneAPI.setOutput(\"right\",true);" +
                "}");

    }

    @Override
    public void onLoad() {
        if(!world.isRemote){
            computer.startComputer();
        }
    }

    public void updateTiles(BlockPos tilePos){
    }

    @Override
    public void tick() {
        if(computer.isDirty()){
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockState().getBlock());
        }
        computer.updateRedstoneInput();
    }
    public Computer getComputer() {
        return computer;
    }

}
