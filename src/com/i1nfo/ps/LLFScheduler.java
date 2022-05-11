package com.i1nfo.ps;

import java.util.LinkedList;
import java.util.List;

public class LLFScheduler implements Scheduler {

    private final Notifier notifier; // New process notifier

    private final List<Process> processes; // Process list

    private long currentTime;

    LLFScheduler(Notifier notifier) {
        this.notifier = notifier;
        this.processes = new LinkedList<>();
        this.currentTime = 0;
    }

    @Override
    public List<Process> getProcesses() {
        return processes;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    private long getLaxity(Process process) {
        return process.getDeadLine() - currentTime - process.getRequiredExecutionTime();
    }

    @Override
    public boolean run() {
        // Select the current process
        long minLaxity = Long.MAX_VALUE;
        Process currentProcess = null;
        // Find out min laxity process
        for (Process process : processes) {
            long laxity = getLaxity(process);
            if (laxity < minLaxity) {
                minLaxity = laxity;
                currentProcess = process;
            }
        }

        // If no process, do nothing
        if (currentProcess != null) {
            long remainTime = currentProcess.makeProgress();
            // Process finished, remove from process list
            if (remainTime == 0) {
                processes.remove(currentProcess);
            }
        }

        // New processes
        List<Process> newProcesses = notifier.newProcesses(currentTime);
        if (newProcesses != null)
            processes.addAll(newProcesses);

        // Tick
        ++currentTime;

        // Return progress status
        return currentProcess != null;
    }
}
