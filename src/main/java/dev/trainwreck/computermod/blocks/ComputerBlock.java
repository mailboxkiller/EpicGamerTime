package dev.trainwreck.computermod.blocks;

import dev.trainwreck.computermod.tileentity.TileComputer;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class ComputerBlock extends BlockTileBase {
    public ComputerBlock() {
        super(Properties.create(Material.PISTON), "computer");
        this.setItemGroup(ItemGroup.REDSTONE);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        TileComputer tileComputer = (TileComputer) worldIn.getTileEntity(currentPos);
        tileComputer.updateTiles(currentPos.offset(facing));

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        //System.out.println(world.isRemote());

        //TileComputer tileComputer = (TileComputer) world.getTileEntity(pos);
        //tileComputer.updateTiles(neighbor);
    }
}
