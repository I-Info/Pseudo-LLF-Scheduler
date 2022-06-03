package com.i1nfo.ps;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {

    private final Notifier notifier; // New process notifier

    private final List<Process> processes; // Process list

    private long currentTime;

    private Process currentProcess;

    Scheduler(Notifier notifier) {
        this.notifier = notifier;
        this.processes = new LinkedList<>();
        this.currentTime = 0;
        this.currentProcess = null;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    private long getLaxity(Process process) {
        return process.getDeadLine() - currentTime - process.getRequiredExecutionTime();
    }

    public Process run() {
        // New processes
        List<Process> newProcesses = notifier.newProcesses(currentTime);
        if (newProcesses != null)
            processes.addAll(newProcesses);

        if (currentProcess == null) {
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
            this.currentProcess = currentProcess;
        } else {
            // Select 0 laxity process
            for (Process process : processes) {
                long laxity = getLaxity(process);
                if (laxity == 0) {
                    currentProcess = process;
                }
            }
        }

        // If null, do noting
        if (currentProcess != null) {
            long remainTime = currentProcess.makeProgress();
            // Process finished, remove from process list
            Process process = currentProcess;
            if (remainTime == 0) {
                processes.remove(currentProcess);
                currentProcess = null;
            }
            ++currentTime;
            return process;
        }

        // Tick
        ++currentTime;
        return null;
    }
}
