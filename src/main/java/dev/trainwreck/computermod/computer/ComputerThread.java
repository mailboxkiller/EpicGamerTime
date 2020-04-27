package dev.trainwreck.computermod.computer;

import dev.trainwreck.computermod.Reference;
import dev.trainwreck.computermod.computer.apis.ITask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ComputerThread {

    private static final Object lock;

    private static final WeakHashMap<Object, LinkedBlockingQueue<ITask>> computerTasks;
    private static final ArrayList<LinkedBlockingQueue<ITask>> computerTasksPending;
    private static final ArrayList<LinkedBlockingQueue<ITask>> computerTasksActive;
    private static final Object monitor;


    private static Thread thread;

    private static boolean running;
    private static boolean stopped;


    static {
        lock = new Object();

        computerTasks = new WeakHashMap<>();
        computerTasksPending = new ArrayList<>();
        computerTasksActive = new ArrayList<>();

        monitor = new Object();

        running = false;
        stopped = false;
    }


    public static void start() {
        if (running) {
            stopped = false;
            return;
        }

        thread = new Thread(() -> {
            while (true) {

                synchronized (computerTasksPending) {
                    if (!computerTasksPending.isEmpty()) {
                        Iterator<LinkedBlockingQueue<ITask>> it = computerTasksPending.iterator();
                        while (it.hasNext()) {
                            LinkedBlockingQueue<ITask> queue = it.next();

                            if (!computerTasksActive.contains(queue)) {
                                computerTasksActive.add(queue);
                            }
                            it.remove();
                        }
                    }
                }
                Iterator<LinkedBlockingQueue<ITask>> it = computerTasksActive.iterator();
                while (it.hasNext()) {
                    LinkedBlockingQueue<ITask> queue = it.next();

                    if (queue == null || queue.isEmpty()) {
                        continue;
                    }
                    synchronized (lock) {
                        if (stopped) {
                            running = false;
                            thread = null;
                            return;
                        }
                    }
                    try {
                        final ITask task = queue.take();

                        // Create the task
                        Thread worker = new Thread(() ->
                        {
                            try {
                                task.execute();
                            } catch (Throwable e) {
                                Reference.LOGGER.error("Error running task", e);
                            }
                        });

                        // Run the task
                        worker.setDaemon(true);
                        worker.start();
                        worker.join(7000);

                        if (worker.isAlive()) {
                            // Task ran for too long
                            // Initiate escape plan
                            Computer computer = task.getComputer();
                            if (computer != null) {
                                // Step 1: Soft abort
                                computer.abort(false);
                                worker.join(1500);

                                if (worker.isAlive()) {
                                    // Step 2: Hard abort
                                    computer.abort(true);
                                    worker.join(1500);
                                }
                            }

                            // Step 3: abandon
                            if (worker.isAlive()) {
                                // ComputerCraft.log.warn( "Failed to abort Computer " + computer.getID() + ". Dangling lua thread could cause errors." );
                                worker.interrupt();
                            }
                        }
                    } catch (InterruptedException e) {
                        continue;
                    }
                    synchronized (queue) {
                        if (queue.isEmpty()) {
                            it.remove();
                        }
                    }
                }
            }
        }, "Computer Dispatch Thread");

        System.out.println(thread.getName());
        thread.setDaemon(true);
        thread.start();
        running = true;


    }

    public static void queueTask(ITask task, Computer computer) {
        Object queueObject = computer;


        if (queueObject == null)
            queueObject = new Object();

        LinkedBlockingQueue<ITask> queue = computerTasks.get(queueObject);
        if (queue == null) {
            computerTasks.put(queueObject, queue = new LinkedBlockingQueue<>(256));
        }
        synchronized (computerTasksPending) {
            if (queue.offer(task)) {
                if (!computerTasksPending.contains(queue)) {
                    computerTasksPending.add(queue);
                }
            }
        }

    }

}
