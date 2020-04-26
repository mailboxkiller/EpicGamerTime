package dev.trainwreck.computermod.api.peripheral;

import dev.trainwreck.computermod.api.javascript.JavaScriptException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IPeripheral {

    @Nonnull
    String getType();


    @Nonnull
    String[] getMethodNames();


    @Nullable
    Object[] callMethod(  int method, @Nonnull Object[] arguments ) throws JavaScriptException, InterruptedException;
}
