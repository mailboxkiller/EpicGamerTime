package dev.trainwreck.computermod.api.javascript;

import dev.trainwreck.computermod.computer.Computer;

import javax.annotation.Nonnull;
import javax.script.CompiledScript;

public interface IJavaScriptAPI {
    Computer getComputer();
    void setComputer(Computer computer);
    String getAPIName();
    String[] getMethods();
}
