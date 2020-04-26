package dev.trainwreck.computermod.computer.apis;

import dev.trainwreck.computermod.computer.Computer;

public interface ITask {
    Computer getComputer();

    void execute();
}
