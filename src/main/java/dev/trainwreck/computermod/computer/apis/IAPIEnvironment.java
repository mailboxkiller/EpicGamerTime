package dev.trainwreck.computermod.computer.apis;

import dev.trainwreck.computermod.computer.Computer;

public interface IAPIEnvironment {

    Computer getComputer();
    int getComputerID();

    void shutdown();
    void reboot();
    void queueEvent(String event, Object[] args);

    void setOutput();
    int getOutput();
    int getInput();
}
