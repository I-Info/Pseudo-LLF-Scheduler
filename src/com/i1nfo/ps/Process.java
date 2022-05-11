package com.i1nfo.ps;

public class Process {
    private long requiredExecutionTime;

    private final long deadLine;

    public Process(long requiredTime, long deadLine) {
        this.requiredExecutionTime = requiredTime;
        this.deadLine = deadLine;
    }

    public long getRequiredExecutionTime() {
        return requiredExecutionTime;
    }

    public long getDeadLine() {
        return deadLine;
    }

    public long makeProgress() {
        return --this.requiredExecutionTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "requiredExecutionTime=" + requiredExecutionTime +
                ", deadLine=" + deadLine +
                '}';
    }
}
