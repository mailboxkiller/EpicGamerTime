package dev.trainwreck.computermod.computer;

import dev.trainwreck.computermod.blocks.ComputerState;
import dev.trainwreck.computermod.computer.javascript.JavaScriptProgram;
import dev.trainwreck.computermod.tileentity.TileEntityBase;

import java.util.ArrayList;

public class Computer {
    private JavaScriptProgram program = new JavaScriptProgram(this);
    private Thread computerThread = null;
    private ComputerState computerState = ComputerState.Off;


    public static final String[] sideNames = new String[] {
            "top", "bottom", "back", "front", "right", "left",
    };

    private TileEntityBase tile;
    private int[] redstoneOutput = new int[6];
    private boolean dirty = false;

    public Computer(TileEntityBase tile){
        this.tile = tile;
    }

    public void startComputer(){
        if(!(computerState == ComputerState.Off))return;

        computerThread = new Thread(() ->{
           while (true){
               synchronized (this){
                   program.startProgram();
               }
/*               try {
                   Thread.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }*/
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

    public boolean isDirty() {
        return dirty;
    }

    public JavaScriptProgram getProgram() {
        return program;
    }

    public void abort(boolean hard){

    }

}
