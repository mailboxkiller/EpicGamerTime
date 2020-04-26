package dev.trainwreck.computermod.computer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ComputerNetwork {
    private ArrayList<TileBlockInfo> tileEntities = new ArrayList<>();

    public ComputerNetwork() {
    }

    public void addBlockTileEntitys(BlockPos pos, World world){
        tileEntities.add(new TileBlockInfo(world.getTileEntity(pos), pos));
    }
    public void removeBlockTileEntitys(BlockPos pos){
        int i = 0;

        for (TileBlockInfo info:tileEntities) {
            if(info.getPos().equals(pos)){
                tileEntities.remove(i);
                return;
            }
            i++;
        }
    }

    public ArrayList<TileBlockInfo> getTileEntities() {
        return tileEntities;
    }

    class TileBlockInfo{
        private TileEntity entity;
        private BlockPos pos;

        public TileBlockInfo(TileEntity entity, BlockPos pos) {
            this.entity = entity;
            this.pos = pos;
        }

        public BlockPos getPos() {
            return pos;
        }

        public TileEntity getEntity() {
            return entity;
        }

    }
}
