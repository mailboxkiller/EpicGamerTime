package dev.trainwreck.computermod.common.computer;

import dev.trainwreck.computermod.common.computer.javascript.JavaScriptProgram;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class Computer {
    private JavaScriptProgram program = new JavaScriptProgram(this);
    private Thread computerThread = null;
    private ComputerState computerState = ComputerState.Off;

    private TileEntity tile;
    private int[] redstoneOutput = new int[6];
    private int[] redstoneInput = new int[6];

    private boolean dirty = false;
    private boolean onTick = false;

    public static final String[] sideNames = new String[] {
            "top", "bottom", "back", "front", "right", "left",
    };

    public Computer(TileEntity tile){
        this.tile = tile;
    }

    public void startComputer(){
        if(!(computerState == ComputerState.Off))return;

        computerState = ComputerState.Starting;

        computerThread = new Thread(() ->{
           while (true){
               synchronized (this){
                   if(computerState == ComputerState.Stopping){
                       computerState = ComputerState.Off;
                       computerThread = null;
                       return;
                   }
                   program.runProgram();
                   if (onTick){
                       program.runProgramOnTick();
                       onTick = false;
                   }
               }
           }
        });
        computerThread.start();
        computerState = ComputerState.Running;
    }

    public void setRedstoneOutput(int side, int value) {
        redstoneOutput[side] = value;
        dirty = true;
    }

    public int getRedstoneOutput(int side) {
        int clamped = Math.abs(side % 6);
        return redstoneOutput[clamped];
    }

    public void updateRedstoneInput(){
        for (Direction dir : Direction.values()) {
            Direction side = getCorrectedSide(dir);
            BlockPos blockpos = tile.getPos().offset(side);
            int i = tile.getWorld().getRedstonePower(blockpos, side);
            if (i >= 15) {
                redstoneInput[dir.getIndex()] = i;
            } else {
                BlockState blockstate = tile.getWorld().getBlockState(blockpos);
                redstoneInput[dir.getIndex()] = Math.max(i, blockstate.getBlock() == Blocks.REDSTONE_WIRE ? blockstate.get(RedstoneWireBlock.POWER) : 0);
            }
        }
    }


    public void abort(boolean hard){
        if(hard){
            computerState = ComputerState.Stopping;
            computerThread.interrupt();
        }
    }

    public Direction getCorrectedSide(Direction side){
        Direction facing = tile.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
        Direction correctedSide = side;

        if(!(side == Direction.UP) && !(side == Direction.DOWN)){
            if(facing == Direction.SOUTH)
                correctedSide = correctedSide.getOpposite();
            if(facing == Direction.EAST)
                correctedSide = correctedSide.rotateYCCW();
            if(facing == Direction.WEST)
                correctedSide = correctedSide.rotateY();
        }
        return correctedSide;
    }

    public int getRedstoneInput(int side) {
        return redstoneInput[side];
    }

    public boolean isDirty() {
        return dirty;
    }

    public ComputerState getComputerState() {
        return computerState;
    }

    public JavaScriptProgram getProgram() {
        return program;
    }

    public void setOnTick(boolean onTick) {
        this.onTick = onTick;
    }
}
