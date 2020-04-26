package dev.trainwreck.computermod.computer.javascript;

import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;

public interface IJavaScriptMashine {
    void addAPI(IJavaScriptAPI api);

    void softAbort(String abortMessage);
    void hardAbort(String abortMessage);

    boolean isFinished();


}
