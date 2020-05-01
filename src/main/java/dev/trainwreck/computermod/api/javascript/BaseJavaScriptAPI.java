package dev.trainwreck.computermod.api.javascript;

import dev.trainwreck.computermod.computer.Computer;
import dev.trainwreck.computermod.computer.javascript.JavaScriptProgram;

import java.util.concurrent.TimeUnit;

public class BaseJavaScriptAPI implements IJavaScriptAPI{
    private Computer computer;

    public BaseJavaScriptAPI(Computer computer){
    }

    public void setTimeout(int milli) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(milli);
    }

    @Override
    public Computer getComputer() {
        return computer;
    }

    @Override
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String getAPIName() {
        return "JavaScriptAPI";
    }

    @Override
    public String[] getMethods() {
        return new String[0];
    }
}
