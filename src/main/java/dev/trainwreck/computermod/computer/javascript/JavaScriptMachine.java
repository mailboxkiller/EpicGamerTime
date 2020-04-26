package dev.trainwreck.computermod.computer.javascript;

import dev.trainwreck.computermod.Reference;
import dev.trainwreck.computermod.computer.Computer;
import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;
import dev.trainwreck.computermod.computer.apis.RedstoneAPI;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class JavaScriptMachine implements IJavaScriptMashine {

    private Computer computer;

    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    private ArrayList<IJavaScriptAPI> api = new ArrayList<>();

    private Thread runner;

    public JavaScriptMachine(Computer computer){
        this.computer = computer;
    }

    public void test(String command){
        try{
            engine.eval(command);
        }catch (ScriptException e){
            e.printStackTrace();
        }
    }


    @Override
    public void addAPI(IJavaScriptAPI api) {
        try{
            engine.eval(api.getMethodCallbacks());
        }catch (ScriptException e){
            e.printStackTrace();
        }
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
