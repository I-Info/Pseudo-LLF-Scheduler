package com.i1nfo.ps;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] cycles = {0, 0, 0};
        // Init scheduler
        Scheduler scheduler = new Scheduler(currentTime -> {
            final String[] names = {"A", "B", "C"};
            final long[] intervals = {15, 30, 45};
            final long[] durations = {5, 10, 12};
            List<Process> newProcesses = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                if (currentTime % intervals[i] == 0) {
                    newProcesses.add(new Process(names[i], ++cycles[i], durations[i], currentTime + intervals[i]));
                }
            }
            return newProcesses.size() == 0 ? null : newProcesses;
        });

        // Execute scheduler
        for (long i = 0; i < 100; ++i) {
            Process current = scheduler.run(i);
            System.out.printf("Time:%sms ", i);
            if (current != null) {
                System.out.printf("[P] %s Cycle:%s Execution:%sms Remain:%sms DDL:%sms Laxity:%s\n", current.name, current.cycle, current.getExecution(), current.getRemain(), current.deadLine, Scheduler.getLaxity(current, i));
            } else {
                System.out.print("[I]\n");
            }
        }
    }

}
