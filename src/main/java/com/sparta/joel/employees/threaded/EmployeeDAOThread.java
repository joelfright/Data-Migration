package com.sparta.joel.employees.threaded;

import com.sparta.joel.employees.EmployeeDTO;
import com.sparta.joel.employees.CSVReader;

import java.sql.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class EmployeeDAOThread implements Runnable{

    @Override
    public void run() {
        insertSpecificEmployees(CSVReader.employeeQueue);
    }

    private final String URL = "jdbc:mysql://localhost:3306/mylocal?serverTimezone=GMT";
    private Connection connection = null;

    private Connection connectToDatabase(){
        try {
            connection = DriverManager.getConnection(URL, System.getenv("db_username"), System.getenv("db_password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertSpecificEmployees(LinkedBlockingQueue<EmployeeDTO> employeeQueue){
        String insertEmployee = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Iterator it = employeeQueue.iterator();
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee)) {
            while(it.hasNext()) {
                EmployeeDTO employee = employeeQueue.poll();
                if(employee == null){
                    break;
                }
                preparedStatement.setInt(1, employee.getEmpId());
                preparedStatement.setString(2, employee.getPrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4, String.valueOf(employee.getMiddleInitial()));
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6, String.valueOf(employee.getGender()));
                preparedStatement.setString(7, employee.getEmail());
                preparedStatement.setDate(8, new java.sql.Date(employee.getDateOfBirth().getTime()));
                preparedStatement.setDate(9, new java.sql.Date(employee.getDateOfJoin().getTime()));
                preparedStatement.setInt(10, employee.getSalary());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
