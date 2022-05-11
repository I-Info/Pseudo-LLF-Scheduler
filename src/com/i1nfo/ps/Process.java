package com.i1nfo.ps;

public class Process {
    private final long deadLine;
    private long requiredExecutionTime;

    private final String name;

    private final int cycle;

    public Process(String name, int cycle, long requiredTime, long deadLine) {
        this.name = name;
        this.cycle = cycle;
        this.requiredExecutionTime = requiredTime;
        this.deadLine = deadLine;
    }

    public long getRequiredExecutionTime() {
        return requiredExecutionTime;
    }

    public long getDeadLine() {
        return deadLine;
    }

    public String getName() {
        return name;
    }

    public int getCycle() {
        return cycle;
    }

    public long makeProgress() {
        return --this.requiredExecutionTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name=" + name +
                ", cycle=" + cycle +
                ", requiredExecutionTime=" + requiredExecutionTime +
                "ms, deadLine=" + deadLine +
                "ms}";
    }
}
