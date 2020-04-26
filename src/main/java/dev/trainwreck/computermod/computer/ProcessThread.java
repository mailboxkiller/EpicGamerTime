package dev.trainwreck.computermod.computer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ProcessThread {
        private static final Object lock;

        private static Thread thread;
        private static final Object defaultQueue;
        private static final Object monitor;

        private static boolean running;
        private static boolean stopped;


    static
    {
        lock = new Object();
        thread = null;
        //TODO: add task list
/*        m_computerTasks = new WeakHashMap<>();
        m_computerTasksPending = new ArrayList<>();
        m_computerTasksActive = new ArrayList<>();*/
        defaultQueue = new Object();
        monitor = new Object();
        running = false;
        stopped = false;
    }

    public static void start(){
        if(running){
            stopped = false;
            return;
        }

        thread = new Thread(() -> {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
               try {
                   engine.eval("var i = 0;");
                   while (true) {
                       engine.eval("i++;");
                   }
               }catch (ScriptException e){
                   e.printStackTrace();
               }

        });
        thread.start();
    }



}
