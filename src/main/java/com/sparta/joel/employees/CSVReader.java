package com.sparta.joel.employees;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class CSVReader {

    private static final String employeesSmall = "src/resources/employees.csv";
    private static final String employeesLarge = "src/resources/EmployeeRecordsLarge.csv";
    public static final HashMap<Integer, EmployeeDTO> employeesList = new HashMap<>();
    public static final LinkedBlockingQueue<EmployeeDTO> employeeQueue = new LinkedBlockingQueue<>();

    public static void getData(boolean large){

        String employees = large ? employeesLarge : employeesSmall;

        try(BufferedReader input = new BufferedReader(new FileReader(employees))){
            input.readLine();
            String line = input.readLine();
            while(line != null){
                String[] attributes = line.split(",");
                EmployeeDTO e = new EmployeeDTO(
                        Integer.parseInt(attributes[0]),
                        attributes[1],
                        attributes[2],
                        attributes[3].charAt(0),
                        attributes[4],
                        attributes[5].charAt(0),
                        attributes[6],
                        dateFormatter(attributes[7]),
                        dateFormatter(attributes[8]),
                        Integer.parseInt(attributes[9]));
                EmployeeDTO check = employeesList.putIfAbsent(Integer.parseInt(attributes[0]),e);
                if(check == null){
                    employeeQueue.add(e);
                }
                line = input.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Date dateFormatter(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
