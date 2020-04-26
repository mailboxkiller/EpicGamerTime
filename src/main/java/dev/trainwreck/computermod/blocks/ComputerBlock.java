package dev.trainwreck.computermod.blocks;

import dev.trainwreck.computermod.tileentity.CmTileEntitys;
import dev.trainwreck.computermod.tileentity.TileComputer;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ComputerBlock extends BlockTileBase {
    public ComputerBlock() {
        super(Properties.create(Material.PISTON), "computer");
        this.setItemGroup(ItemGroup.REDSTONE);
    }


    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        TileComputer tileComputer = (TileComputer) world.getTileEntity(pos);
        TileEntity entity = world.getTileEntity(neighbor);
        if(entity==null)return;

        tileComputer.updateTiles(neighbor);
    }
}
