package com.sparta.joel;

import com.sparta.joel.main.Starter;
import com.sparta.joel.printers.Printer;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PerformanceTests {

    @Test
    @DisplayName("Sequential | Small CSV")
    public void timeTakenSequential(){
        long start = System.nanoTime();
        Starter.loadData(false);
        Starter.startInsert(false,1);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;

        Printer.printMessage("Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Threaded | 100 Threads | Small CSV")
    public void timeTakenThreaded(){
        long start = System.nanoTime();
        Starter.loadData(false);
        Starter.startInsert(true, 100);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;

        Printer.printMessage("Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Threaded | 100 Threads | Large CSV")
    public void timeTakenThreadedLarge(){
        long start = System.nanoTime();
        Starter.loadData(true);
        Starter.startInsert(true, 100);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;

        Printer.printMessage("Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Threaded | 10 Threads | Large CSV")
    public void timeTakenThreaded10Threads(){
        long start = System.nanoTime();
        Starter.loadData(true);
        Starter.startInsert(true, 10);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;

        Printer.printMessage("Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Threaded | 50 Threads | Large CSV")
    public void timeTakenThreaded50Threads(){
        long start = System.nanoTime();
        Starter.loadData(true);
        Starter.startInsert(true, 50);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;

        Printer.printMessage("Time taken: " + totalTime);
    }

}
