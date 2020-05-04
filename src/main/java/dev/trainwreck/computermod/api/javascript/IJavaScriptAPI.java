package dev.trainwreck.computermod.api.javascript;

import dev.trainwreck.computermod.common.computer.Computer;

public interface IJavaScriptAPI {
    Computer getComputer();
    void setComputer(Computer computer);
    String getAPIName();
    String[] getMethods();
}
