package dev.trainwreck.computermod.api.javascript;

import dev.trainwreck.computermod.computer.Computer;

public interface IJavaScriptAPI {
    Computer getComputer();
    void setComputer(Computer computer);
    String getAPIName();
    String[] getMethods();
}
