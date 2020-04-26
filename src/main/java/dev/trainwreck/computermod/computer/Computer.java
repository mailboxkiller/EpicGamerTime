package dev.trainwreck.computermod.computer;

import dev.trainwreck.computermod.blocks.ComputerState;
import dev.trainwreck.computermod.computer.apis.IAPIEnvironment;
import dev.trainwreck.computermod.computer.apis.ITask;
import dev.trainwreck.computermod.computer.javascript.IJavaScriptMashine;

public class Computer {
    public static final String[] sideNames = new String[] {
            "bottom", "top", "back", "front", "right", "left",
    };


    private int ticksSinceStart;
    private boolean startRequested;
    private ComputerState state;
    private boolean blinking;

    private final APIEnvironment m_apiEnvironment;
    private final IJavaScriptMashine machine;

    private final int[] internalOutput;
    private boolean internalOutputChanged;

    private final int[] externalOutput;
    private boolean externalOutputChanged;

    private final int[] input;
    private boolean inputChanged;

    public Computer(){
        ComputerThread.start();

        ticksSinceStart = -1;
        startRequested = false;
        state = ComputerState.Off;
        blinking = false;

        m_apiEnvironment = new APIEnvironment(this);
        machine = null;

        internalOutput = new int[6];
        internalOutputChanged = true;

        externalOutput = new int[6];
        externalOutputChanged = true;

        input = new int[6];
        inputChanged = false;

    }

    public boolean isOn()
    {
        synchronized( this )
        {
            return state == ComputerState.Running;
        }
    }

    public void abort( boolean hard )
    {
        synchronized( this )
        {
            if( state != ComputerState.Off && machine != null )
            {
                if( hard )
                {
                    machine.hardAbort( "Too long without yielding" );
                }
                else
                {
                    machine.softAbort( "Too long without yielding" );
                }
            }
        }
    }

    public void startComputer(){
        synchronized( this ) {
            if (state != ComputerState.Off) {
                return;
            }
            state = ComputerState.Starting;
            ticksSinceStart = 0;
        }

        final Computer computer = this;
        ComputerThread.queueTask(new ITask() {
            @Override
            public Computer getComputer() {
                return computer;
            }

            @Override
            public void execute() {
                state = ComputerState.Running;
                System.out.println("computer Started");
            }
        },computer);
    }

    public void stopComputer(final boolean reboot){
        synchronized( this )
        {
            if( state != ComputerState.Running )
            {
                return;
            }
            state = ComputerState.Stopping;
        }
        final Computer computer = this;
        ComputerThread.queueTask(new ITask() {
            @Override
            public Computer getComputer() {
                return computer;
            }

            @Override
            public void execute() {
                if( state != ComputerState.Stopping )
                {
                    return;
                }
                state = ComputerState.Off;

            }
        },computer);

    }


    public ComputerState getState() {
        return state;
    }

    private static class APIEnvironment implements IAPIEnvironment{
        private Computer computer;
        public APIEnvironment( Computer computer )
        {
            computer = computer;
        }

        @Override
        public Computer getComputer() {
            return computer;
        }

        @Override
        public int getComputerID() {
            return 0;
        }

        @Override
        public void shutdown() {
            computer.stopComputer(false);
        }

        @Override
        public void reboot() {
            computer.stopComputer(true);

        }

        @Override
        public void queueEvent(String event, Object[] args) {

        }

        @Override
        public void setOutput() {

        }

        @Override
        public int getOutput() {
            return 0;
        }

        @Override
        public int getInput() {
            return 0;
        }
    }

}
