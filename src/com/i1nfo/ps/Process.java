package com.i1nfo.ps;

public class Process {
    private long requiredExecutionTime;

    private final long deadLine;

    public Process(int requiredTime, long deadLine) {
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
}
