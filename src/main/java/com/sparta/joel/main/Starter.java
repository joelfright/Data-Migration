package com.sparta.joel.main;

import com.sparta.joel.employees.sequential.EmployeeDAO;
import com.sparta.joel.employees.EmployeeDTO;
import com.sparta.joel.employees.threaded.Threads;

public class Starter {

    public static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void start(){

        loadData(true);
        startInsert(true, 500);

    }

    public static void startInsert(boolean threaded, int numOfThreads){

        if(threaded){
            Threads.createThreads(numOfThreads);
        }else{
            employeeDAO.insertAllEmployees(EmployeeDTO.employeesList);
        }
    }

    public static void loadData(boolean large){
        if(employeeDAO.checkExists()){
            employeeDAO.dropTable();
        }

        employeeDAO.createTable();

        EmployeeDTO.getData(large);
    }

}
