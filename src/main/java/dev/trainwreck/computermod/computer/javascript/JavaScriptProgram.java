package dev.trainwreck.computermod.computer.javascript;

import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;
import dev.trainwreck.computermod.computer.Computer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class JavaScriptProgram {
    private Computer computer;
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    private ArrayList<IJavaScriptAPI> apis = new ArrayList<>();
    private JavaScriptWriter stringWriter = new JavaScriptWriter();
    private String program;

    public JavaScriptProgram(Computer computer) {
        this.computer = computer;
        engine.getContext().setWriter(stringWriter);
    }


    public void setProgram(String program) {
        this.program = program;
    }

    public void runProgram(){
        try {
            engine.eval(program);
            for (String test: stringWriter.getOutputs()) {
                System.out.println(test);
            }
            stringWriter.clear();
        }catch (ScriptException e){
            e.printStackTrace();
        }
    }

    public void addApi(IJavaScriptAPI javaScriptAPI){
        javaScriptAPI.setComputer(computer);
        engine.put(javaScriptAPI.getAPIName(),javaScriptAPI);
        apis.add(javaScriptAPI);

    }



}
