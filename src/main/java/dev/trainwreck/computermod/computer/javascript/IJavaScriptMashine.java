package dev.trainwreck.computermod.computer.javascript;

public interface IJavaScriptMashine {
    void addAPI();

    void softAbort(String abortMessage);
    void hardAbort(String abortMessage);

    boolean isFinished();


}
