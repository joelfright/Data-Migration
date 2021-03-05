package com.sparta.joel.main;

import com.sparta.joel.employees.sequential.EmployeeDAO;
import com.sparta.joel.employees.CSVReader;
import com.sparta.joel.employees.threaded.Threads;

public class Starter {

    public static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void start() {

        loadData(false);
        startInsert(true, 100);

    }

    public static void startInsert(boolean threaded, int numOfThreads) {
        if (threaded) {
            Threads.createThreads(numOfThreads);
        } else {
            employeeDAO.insertAllEmployees(CSVReader.employeesList);
        }
    }

    public static void loadData(boolean large) {
        employeeDAO.truncateTable();

        CSVReader.getData(large);
    }

}
