package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.blocks.CmBlocks;
import dev.trainwreck.computermod.computer.ComputerNetwork;
import dev.trainwreck.computermod.computer.ProcessThread;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class TileComputer extends TileEntityBase implements ITickableTileEntity {
    private ComputerNetwork network = new ComputerNetwork();

    public TileComputer() {
        super(CmTileEntitys.COMPUTER_TE.getTileType(CmBlocks.COMPUTER_BLOCK.getBlock()));

    }

    public void updateTiles(BlockPos tilePos){
        if(world.getTileEntity(tilePos) == null){
            network.removeBlockTileEntitys(tilePos);
        }else{
            network.addBlockTileEntitys(tilePos, this.world);
        }
        if(!world.isRemote){
            System.out.println("server "+network.getTileEntities().size());
        }
        if(world.isRemote){
            System.out.println("client "+network.getTileEntities().size());
        }

    }

    @Override
    public void tick() {

    }
}
