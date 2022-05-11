package com.i1nfo.ps;

import java.util.LinkedList;
import java.util.List;

public class LLFScheduler implements Scheduler {

    private final Notifier notifier;

    private final List<Process> processes; // Process list

    private long currentTime;

    LLFScheduler(Notifier notifier) {
        this.notifier = notifier;
        this.processes = new LinkedList<>();
        this.currentTime = 0;
    }

    private long getLaxity(Process process) {
        return currentTime - process.getDeadLine() - process.getRequiredExecutionTime();
    }

    @Override
    public void run() {
        // New processes
        List<Process> newProcesses = notifier.newProcesses(currentTime);
        if (newProcesses != null)
            processes.addAll(newProcesses);

        // Select the next process
        long minLaxity = Long.MAX_VALUE;
        Process nextProcess = null; // The next process to be progressed.
        // Find out min laxity process
        for (Process process : processes) {
            long laxity = getLaxity(process);
            if (laxity < minLaxity) {
                minLaxity = laxity;
                nextProcess = process;
            }
        }

        // If no process, do nothing
        if (nextProcess != null) {
            long remainTime = nextProcess.makeProgress();
            // Process finished, remove from process list
            if (remainTime == 0) {
                processes.remove(nextProcess);
            }
        }

        // tick
        ++currentTime;
    }
}
