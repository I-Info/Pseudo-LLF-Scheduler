package com.i1nfo.ps;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {

    private final Notifier notifier; // New process notifier

    private final List<Process> processes; // Process list

    private Process currentProcess;

    Scheduler(Notifier notifier) {
        this.notifier = notifier;
        this.processes = new LinkedList<>();
        this.currentProcess = null;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public static long getLaxity(Process process, long currentTime) {
        return process.getDeadLine() - currentTime - process.getRequiredExecutionTime();
    }

    public Process run(long currentTime) {
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
                long laxity = getLaxity(process, currentTime);
                if (laxity < minLaxity) {
                    minLaxity = laxity;
                    currentProcess = process;
                }
            }
            this.currentProcess = currentProcess;
        } else {
            // Select 0 laxity process
            for (Process process : processes) {
                long laxity = getLaxity(process, currentTime);
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

            return process;
        }

        // Tick
        return null;
    }
}
