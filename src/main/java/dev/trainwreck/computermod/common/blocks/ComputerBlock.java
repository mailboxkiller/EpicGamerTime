package dev.trainwreck.computermod.common.blocks;

import dev.trainwreck.computermod.common.computer.Computer;
import dev.trainwreck.computermod.common.computer.ComputerState;
import dev.trainwreck.computermod.common.tileentity.TileComputer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ComputerBlock extends BlockTileBase {
    public static final EnumProperty<ComputerState> COMPUTER_STATE = EnumProperty.create("computer", ComputerState.class);

    public ComputerBlock() {
        super(Properties.create(Material.PISTON), "computer");
        this.setItemGroup(ItemGroup.REDSTONE);
        this.setDefaultState(this.stateContainer.getBaseState().with(COMPUTER_STATE, ComputerState.Off));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        if(!worldIn.isRemote){
            TileComputer tileComputer= (TileComputer) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, tileComputer, pos);
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileComputer tileComputer = (TileComputer) worldIn.getTileEntity(pos);
            Computer computer = tileComputer.getComputer();
            computer.abort(true);

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        TileComputer tileComputer= (TileComputer) blockAccess.getTileEntity(pos);
        Computer computer = tileComputer.getComputer();

        Direction correctedSide = computer.getCorrectedSide(side);

        return computer.getRedstoneOutput(correctedSide.getIndex());
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(COMPUTER_STATE);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        TileComputer tileComputer = (TileComputer) worldIn.getTileEntity(currentPos);
        tileComputer.updateTiles(currentPos.offset(facing));

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }



}
