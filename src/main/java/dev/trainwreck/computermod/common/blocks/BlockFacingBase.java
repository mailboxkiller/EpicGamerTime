package dev.trainwreck.computermod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public class BlockFacingBase extends BlockBase {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockFacingBase(Properties properties, String internalID) {
        super(properties, internalID);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if(context.getPlayer().isCrouching()){
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
        }
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
}
