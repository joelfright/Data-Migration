package com.sparta.joel.employees.sequential;

import com.sparta.joel.employees.Employee;
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

    public void insertAllEmployees(HashMap<Integer, Employee> employeesList){
        String insertEmployee = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Iterator it = employeesList.entrySet().iterator();
        while(it.hasNext()) {
            try {
                Map.Entry employee = (Map.Entry) it.next();
                Employee values = (Employee) employee.getValue();
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

    public boolean checkExists(){
        try {
            Statement statement = connectToDatabase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT IF(EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'employees'), 1,0);");
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable(){
        try {
            Statement statement = connectToDatabase().createStatement();
            statement.execute("CREATE TABLE employees (" +
                    "emp_id INT PRIMARY KEY," +
                    "name_prefix VARCHAR(6)," +
                    "first_name VARCHAR(15)," +
                    "middle_initial CHAR(1)," +
                    "last_name VARCHAR(20)," +
                    "gender CHAR(1)," +
                    "email VARCHAR(50)," +
                    "date_of_birth DATETIME," +
                    "date_of_join DATETIME," +
                    "salary INT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(){
        try {
            Statement statement = connectToDatabase().createStatement();
            statement.execute("DROP TABLE employees");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
