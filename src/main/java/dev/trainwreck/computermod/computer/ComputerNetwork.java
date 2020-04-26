package dev.trainwreck.computermod.computer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ComputerNetwork {
    private ArrayList<TileBlockInfo> tileEntities = new ArrayList<>();
    private World world;

    public ComputerNetwork(World world) {
        this.world = world;
    }

    public void addBlockTileEntitys(BlockPos pos){
        tileEntities.add(new TileBlockInfo(world.getTileEntity(pos), pos));
    }
    public void removeBlockTileEntitys(BlockPos pos){
        tileEntities.forEach((entity)->{
            if(entity.pos == pos){
                tileEntities.remove(entity);
            }
        });
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
