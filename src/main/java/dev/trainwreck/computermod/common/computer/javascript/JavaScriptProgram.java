package dev.trainwreck.computermod.common.computer.javascript;

import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;
import dev.trainwreck.computermod.common.computer.Computer;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class JavaScriptProgram {
    private Computer computer;

    private ScriptEngine engine = new NashornScriptEngineFactory().getScriptEngine("-strict", "--no-java", "--no-syntax-extensions");

    private CompiledScript compiledScript;
    private ArrayList<IJavaScriptAPI> apis = new ArrayList<>();
    private JavaScriptWriter stringWriter = new JavaScriptWriter();

    private boolean isFinished = true;

    private String program;

    public JavaScriptProgram(Computer computer) {
        this.computer = computer;
        engine.getContext().setWriter(stringWriter);
        engine.put("setTimeout", new SetTimeout());
        engine.put("getSide", new GetSide());
    }


    public void setProgram(String program) {
        this.program = program;
        try {
            compiledScript = ((Compilable) engine).compile(program);
            compiledScript.eval();
            stringWriter.clear();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


    public void runProgramOnTick(){
        isFinished = false;
        try {
            ((Invocable) engine).invokeFunction("onTick");
            for (String test: stringWriter.getOutputs()) {
                System.out.println(test);
            }
            stringWriter.clear();
        }catch (ScriptException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){

        }
        isFinished = true;
    }

    public void runProgram(){
        isFinished = false;
        try {
            //System.out.println(func);
            ((Invocable) engine).invokeFunction("main");
            ((Invocable) engine).invokeFunction("onTick");
            for (String test: stringWriter.getOutputs()) {
                System.out.println(test);
            }
            stringWriter.clear();
        }catch (ScriptException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
        }
        isFinished = true;
    }

    public void addApi(IJavaScriptAPI javaScriptAPI){
        javaScriptAPI.setComputer(computer);
        engine.put(javaScriptAPI.getAPIName(),javaScriptAPI);
        apis.add(javaScriptAPI);

    }

    private class SetTimeout implements Function<Integer,Boolean> {
        public Boolean apply(Integer barr) {
            try {
                Thread.sleep(barr);
                return true;
            } catch (InterruptedException e) {
                return false;
            }
        }
    }

    private class GetSide implements Function<String,Integer> {
        public Integer apply(String side) {
            return Arrays.asList(Computer.sideNames).indexOf(side);
        }
    }

    public String getProgram() {
        return program;
    }
}
