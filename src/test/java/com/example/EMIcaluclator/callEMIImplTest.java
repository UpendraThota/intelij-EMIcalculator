package com.example.EMIcaluclator;

import com.example.EMIcaluclator.service.callEMIImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class callEMIImplTest {

    callEMIImpl emiService = new callEMIImpl();

    @Test
    public void testEMI() {
        double principal = 100000;
        double rate = 12;
        int time = 1;

        double expectedEmi = 8884.88;
        double actualEmi = emiService.EMI(principal, rate, time);

        assertEquals(expectedEmi, actualEmi, 0.5);

    }

    @Test
    public void testCompareEMI_HomeLoan_WithTime() {
        double principal = 100000;
        int time = 10;

        Map<String, Double> result = emiService.compareEMI(principal, "home", time);
        double expectedHdfcEmi = emiService.EMI(principal, 7.5, time);
        assertEquals(expectedHdfcEmi, result.get("HDFC"), 0.5);
    }

    @Test
    public void testCompareEMI_PersonalLoan_WithTime() {
        double principal = 100000;
        int time = 5;

        Map<String, Double> result = emiService.compareEMI(principal, "personal", time);

        double expectedIciciEmi = emiService.EMI(principal, 10.3, time);
        assertEquals(expectedIciciEmi, result.get("ICICI"), 0.5);
    }


    @Test
    public void testCompareEMI_HomeLoan_NullTime() {
        double principal = 100000;
        Integer time = null;

        Map<String, Double> result = emiService.compareEMI(principal, "home", time);



        double expectedEmi = emiService.EMI(principal, 7.5, 10);
        assertEquals(expectedEmi, result.get("HDFC"), 0.5);
    }


    @Test
    public void testCompareEMI_PersonalLoan_NullTime() {
        double principal = 100000;
        Integer time = null;

        Map<String, Double> result = emiService.compareEMI(principal, "personal", time);



        double expectedEmi = emiService.EMI(principal, 10.5, 5);
        assertEquals(expectedEmi, result.get("HDFC"), 0.5);
    }


    @Test
    public void testCompareEMI_InvalidLoanType() {
        double principal = 100000;
        int time = 5;

        try {
            emiService.compareEMI(principal, "education", time);
            fail("Expected IllegalArgumentException was not thrown");
        }
        catch (IllegalArgumentException ex)
        {
            assertEquals("Invalid loan type: must be 'home' or 'personal'", ex.getMessage());
        }
    }



    @Test
    public void testBestHomeLoan() {
        double principal = 100000;
        int time = 10;

        Map<String, Double> result = emiService.bestHomeLoan(principal, time);

        String res="";
        Double val=0.0;
       for(String s:result.keySet())
       {
           res=s;
           val=result.get(s);
       }
        assertEquals("HDFC", res);

        double expectedEmi = emiService.EMI(principal, 7.5, time);
        assertEquals(expectedEmi, val, 0.5);
    }

    @Test
    public void testBestPersonalLoan() {
        double principal = 100000;
        int time = 5;

        Map<String, Double> result = emiService.bestPersonalLoan(principal, time);



        String res="";
        Double val=0.0;
        for(String s:result.keySet())
        {
            res=s;
            val=result.get(s);
        }
        assertEquals("ICICI", res);

        double expectedEmi = emiService.EMI(principal, 10.3, time);
        assertEquals(expectedEmi, val, 0.5);
    }
}
