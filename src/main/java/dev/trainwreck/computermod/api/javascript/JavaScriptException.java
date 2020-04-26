package dev.trainwreck.computermod.api.javascript;

import javax.annotation.Nullable;

public class JavaScriptException extends Exception {

    private final int level;

    public JavaScriptException(){
        this("error",1);
    }
    public JavaScriptException(@Nullable String message){
        this(message,1);
    }
    public JavaScriptException(@Nullable String message, int level){
        super(message);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
