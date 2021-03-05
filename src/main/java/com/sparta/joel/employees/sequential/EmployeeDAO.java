package com.sparta.joel.employees.sequential;

import com.sparta.joel.employees.EmployeeDTO;
import com.sparta.joel.printers.Printer;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmployeeDAO {

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

    public void getEmployee(int employeeId){
        String getSpecificEmployee = "SELECT * FROM employees WHERE emp_id = ?";
        try {
            PreparedStatement statement = connectToDatabase().prepareStatement(getSpecificEmployee);
            statement.setInt(1,employeeId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet == null){
                Printer.printMessage("No such data exists");
            }else{
                Printer.printMessage("----------------------------------------------");
                while (resultSet.next()) {
                    Printer.printMessage("Employee Id:: " + resultSet.getInt(1));
                    Printer.printMessage("Title:: " + resultSet.getString(2));
                    Printer.printMessage("First Name:: " + resultSet.getString(3));
                    Printer.printMessage("Middle Initial:: "+ resultSet.getString(4));
                    Printer.printMessage("Last Name:: " + resultSet.getString(5));
                    Printer.printMessage("Gender:: " + resultSet.getString(6));
                    Printer.printMessage("Email:: " + resultSet.getString(7));
                    Printer.printMessage("Date of Birth:: " + resultSet.getDate(8));
                    Printer.printMessage("Date of Join:: " + resultSet.getDate(9));
                    Printer.printMessage("Salary:: " + resultSet.getInt(10));
                    Printer.printMessage("----------------------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAllEmployees(HashMap<Integer, EmployeeDTO> employeesList){
        String insertEmployee = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Iterator it = employeesList.entrySet().iterator();
        while(it.hasNext()) {
            try {
                Map.Entry employee = (Map.Entry) it.next();
                EmployeeDTO values = (EmployeeDTO) employee.getValue();
                PreparedStatement preparedStatement = connectToDatabase().prepareStatement(insertEmployee);
                preparedStatement.setInt(1, (int) employee.getKey());
                preparedStatement.setString(2, values.getPrefix());
                preparedStatement.setString(3, values.getFirstName());
                preparedStatement.setString(4, String.valueOf(values.getMiddleInitial()));
                preparedStatement.setString(5, values.getLastName());
                preparedStatement.setString(6, String.valueOf(values.getGender()));
                preparedStatement.setString(7, values.getEmail());
                preparedStatement.setDate(8, new java.sql.Date(values.getDateOfBirth().getTime()));
                preparedStatement.setDate(9, new java.sql.Date(values.getDateOfJoin().getTime()));
                preparedStatement.setInt(10, values.getSalary());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void truncateTable(){
        try (Connection connection = connectToDatabase()){
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE employees");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
