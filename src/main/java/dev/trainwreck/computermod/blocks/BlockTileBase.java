package dev.trainwreck.computermod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockTileBase extends BlockFacingBase {
    private TileEntityType<?> tileEntityType;

    public BlockTileBase(Properties properties, String internalID) {
        super(properties, internalID);
    }

    public void setTileEntityType(TileEntityType<?> tileEntityType) {
        this.tileEntityType = tileEntityType;
    }

    public TileEntityType<?> getTileEntityType() {
        return tileEntityType;
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return tileEntityType.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
