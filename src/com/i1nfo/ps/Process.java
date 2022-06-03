package com.i1nfo.ps;

public class Process {
    public final long deadLine;
    private long remain;

    public final String name;

    public final int cycle;

    public final long requiredExecutionTime;

    public Process(String name, int cycle, long executionTime, long deadLine) {
        this.name = name;
        this.cycle = cycle;
        this.remain = executionTime;
        this.deadLine = deadLine;
        this.requiredExecutionTime = executionTime;
    }

    public long getRemain() {
        return remain;
    }

    public long makeProgress() {
        return --this.remain;
    }

    public long getExecution() {
        return requiredExecutionTime - remain;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name=" + name +
                ", cycle=" + cycle +
                ", requiredExecutionTime=" + remain +
                "ms, deadLine=" + deadLine +
                "ms}";
    }
}
