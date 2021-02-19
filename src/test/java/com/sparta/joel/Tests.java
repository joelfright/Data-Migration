package com.sparta.joel;

import com.sparta.joel.employees.Employee;
import com.sparta.joel.employees.EmployeeDTO;
import com.sparta.joel.main.Starter;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

    @Test
    public void checkDuplicatesSmall(){
        Starter.loadData(false);
        Assert.assertEquals(EmployeeDTO.employeesList.size(),9943);
    }

    @Test
    public void checkDuplicatesLarge(){
        Starter.loadData(true);
        Assert.assertEquals(EmployeeDTO.employeesList.size(),65499);
    }

}
