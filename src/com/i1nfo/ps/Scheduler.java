package com.i1nfo.ps;

import java.util.List;

public interface Scheduler {

    boolean run();

    List<Process> getProcesses();

    long getCurrentTime();

}
