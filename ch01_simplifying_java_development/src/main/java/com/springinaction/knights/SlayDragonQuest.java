package com.springinaction.knights;

import java.io.PrintStream;

/**
 * Created by zk on 17-6-2.
 */
public class SlayDragonQuest implements Quest {

    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.print("Embarking on quest to slay the dragon!");
    }
}
