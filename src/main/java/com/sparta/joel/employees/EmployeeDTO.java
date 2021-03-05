package com.sparta.joel.employees;

import java.util.Date;

public class EmployeeDTO {

    private int empId;
    private String prefix;
    private String firstName;
    private String lastName;
    private char middleInitial;
    private char gender;
    private String email;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private int salary;

    public EmployeeDTO(int empId, String prefix, String firstName, char middleInitial, String lastName, char gender, String email, Date dateOfBirth, Date dateOfJoin, int salary) {
        this.empId = empId;
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[" +
                "prefix:'" + prefix + '\'' +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", middleInitial:" + middleInitial +
                ", gender:" + gender +
                ", email:'" + email + '\'' +
                ", dateOfBirth:'" + dateOfBirth + '\'' +
                ", dateOfJoin:'" + dateOfJoin + '\'' +
                ", salary:'" + salary + '\'' +
                ']';
    }
}
