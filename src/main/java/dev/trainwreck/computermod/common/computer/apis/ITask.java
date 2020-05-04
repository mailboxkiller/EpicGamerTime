package dev.trainwreck.computermod.common.computer.apis;

import dev.trainwreck.computermod.common.computer.Computer;

public interface ITask {
    Computer getComputer();

    void execute();
}
