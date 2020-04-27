package dev.trainwreck.computermod.computer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test {
    public void test(){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");

        engine.put("es",this);

        try {
            engine.eval("print(es.test2())");
            //System.out.println(engine.getFactory().getMethodCallSyntax("es", "test2", new String[0]));

        }catch (ScriptException e){
            e.printStackTrace();
        }
    }


    public String test2(){

        System.out.println("yay");
        return "YEET";
    }

}
