package com.i1nfo.ps;

import java.util.List;

public interface Notifier {

    List<Process> newProcesses(long currentTime);

}
