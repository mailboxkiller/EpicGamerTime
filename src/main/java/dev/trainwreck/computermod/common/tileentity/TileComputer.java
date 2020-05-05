package dev.trainwreck.computermod.common.tileentity;

import dev.trainwreck.computermod.api.redstone.RedstoneAPI;
import dev.trainwreck.computermod.common.blocks.CmBlocks;
import dev.trainwreck.computermod.common.computer.Computer;
import dev.trainwreck.computermod.common.contaner.ComputerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class TileComputer extends TileEntityUIBase implements ITickableTileEntity {
    private volatile Computer computer = new Computer(this);

    public TileComputer() {
        super(CmBlocks.COMPUTER_BLOCK.getTileEntityType(), IForgeContainerType.create(ComputerContainer::new));
        computer.getProgram().addApi(new RedstoneAPI());
        computer.getProgram().setProgram(
                "const val = 0;"+
                "function onTick(){"+
                "   if(Redstone.getInput(\"left\")){" +
                "        Redstone.setOutput(\"right\",false);" +
                "   }else{" +
                "       Redstone.setOutput(\"right\",true);" +
                "   }" +
                "}");

    }

    @Override
    public void onLoad() {
        if(!world.isRemote){
            computer.startComputer();
        }
    }

    @Override
    public void onChunkUnloaded() {
        if(!world.isRemote){
            computer.abort(true);
        }
    }

    @Override
    public void tick() {
        if (world.isRemote)return;
        if(computer.isDirty()){
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockState().getBlock());
        }
        computer.setOnTick(true);
        computer.updateRedstoneInput();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ComputerContainer(id,player,this);
    }

    public Computer getComputer() {
        return computer;
    }

}
