package dev.trainwreck.computermod.api.javascript;

import javax.annotation.Nonnull;

public interface IJavaScriptAPI {

    @Nonnull
    String[] getMethodNames();

    String getMethodCallbacks();
}
