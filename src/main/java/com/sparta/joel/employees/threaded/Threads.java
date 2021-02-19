package com.sparta.joel.employees.threaded;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Threads {

    public static void createThreads(int numOfThreads) {
        EmployeeDAOThread employeeDAOThread = new EmployeeDAOThread();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            fixedThreadPool.execute(new Thread(employeeDAOThread));
        }
        fixedThreadPool.shutdown();
        try {
            fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
