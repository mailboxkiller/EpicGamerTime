package dev.trainwreck.computermod.computer.javascript;

import de.esoco.coroutine.Continuation;
import de.esoco.coroutine.Coroutine;
import de.esoco.coroutine.CoroutineScope;
import de.esoco.coroutine.step.Condition;
import de.esoco.coroutine.step.Delay;
import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;
import dev.trainwreck.computermod.computer.Computer;

import javax.script.*;
import java.util.ArrayList;
import java.util.function.Function;

import static de.esoco.coroutine.step.CodeExecution.run;

public class JavaScriptProgram {
    private Computer computer;
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    private ArrayList<IJavaScriptAPI> apis = new ArrayList<>();
    private JavaScriptWriter stringWriter = new JavaScriptWriter();

    private Coroutine<?, ?> script = Coroutine.first(run(() -> {
        runProgram();
    }));
    private Continuation programState = null;

    private String program;


    public JavaScriptProgram(Computer computer) {
        this.computer = computer;
        engine.getContext().setWriter(stringWriter);
        engine.put("setTimeout", new SetTimeout());
    }


    public void setProgram(String program) {
        this.program = program;

    }

    public void startProgram(){
        if(programState == null || programState.isFinished()){
            CoroutineScope.launch(scope -> {programState = script.runAsync(scope);});
        }
    }

    private void runProgram(){
        try {
            engine.eval(program);
            for (String test: stringWriter.getOutputs()) {
                System.out.println(test);
            }
            stringWriter.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
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
                e.printStackTrace();
                return false;
            }
        }
    }


}
