package com.i1nfo.ps;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Init scheduler
        Scheduler scheduler = new LLFScheduler(currentTime -> {
            long[] intervals = {15, 30, 45};
            long[] durations = {5, 10, 12};
            List<Process> newProcesses = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                if (currentTime % intervals[i] == 0) {
                    newProcesses.add(new Process(durations[i], currentTime + intervals[i]));
                }
            }
            return newProcesses.size() == 0 ? null : newProcesses;
        });

        // Execute scheduler
        for (int i = 0; i < 100; ++i) {
            boolean progressing = scheduler.run();
            if (progressing) {
                List<Process> processes = scheduler.getProcesses();
                System.out.printf("Current time: %s", scheduler.getCurrentTime());
                System.out.println(processes);
            }
        }
    }

}
