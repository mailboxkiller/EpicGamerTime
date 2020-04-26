package dev.trainwreck.computermod.computer.javascript;

import dev.trainwreck.computermod.computer.Computer;

import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptMachine implements IJavaScriptMashine {

    private Computer computer;

    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");

    private Thread runner;

    public JavaScriptMachine(Computer computer){
        this.computer = computer;
    }

    @Override
    public void addAPI() {

    }

    @Override
    public void softAbort(String abortMessage) {

    }

    @Override
    public void hardAbort(String abortMessage) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
