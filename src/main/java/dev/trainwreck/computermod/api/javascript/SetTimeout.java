package dev.trainwreck.computermod.api.javascript;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class SetTimeout implements Function<Long,Boolean> {
    public Boolean apply(Long barr) {
        try {
            TimeUnit.MILLISECONDS.sleep(barr);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}