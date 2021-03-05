package com.sparta.joel;

import com.sparta.joel.employees.CSVReader;
import com.sparta.joel.main.Starter;
import org.junit.Assert;
import org.junit.Test;

public class CheckDuplicateTests {

    @Test
    public void checkDuplicatesSmall(){
        Starter.loadData(false);
        Assert.assertEquals(CSVReader.employeesList.size(),9943);
    }

    @Test
    public void checkDuplicatesLarge(){
        Starter.loadData(true);
        Assert.assertEquals(CSVReader.employeesList.size(),65499);
    }

}
