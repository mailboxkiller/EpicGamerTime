package dev.trainwreck.computermod.computer.apis;

import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;
import dev.trainwreck.computermod.computer.Computer;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class RedstoneAPI implements IJavaScriptAPI {
    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[] {"getSides","setOutput","getInput","setAnalogOutput","setAnalogInput"};
    }

    @Override
    public String getMethodCallbacks() {

        return "var RedstoneAPI = Java.type(\""+this.getClass().getCanonicalName()+"\");";
    }

    public static Map<Object,Object> getSides(){
        Map<Object,Object> table = new HashMap<>();
        for(int i = 0; i< Computer.sideNames.length; ++i )
        {
            table.put( i+1, Computer.sideNames[i] );
        }
        return table;
    }

}
