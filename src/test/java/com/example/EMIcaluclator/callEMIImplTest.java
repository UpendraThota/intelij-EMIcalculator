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
        double rate = 12; // annual rate
        int time = 1; // years

        double expectedEmi = 8884.88; // Correct EMI based on formula
        double actualEmi = emiService.EMI(principal, rate, time);

        assertEquals(expectedEmi, actualEmi, 0.5); // delta of 0.5
        System.out.println("Actual EMI: " + actualEmi);
    }

    @Test
    public void testCompareEMI_homeLoan() {
        Map<String, Double> emiMap = emiService.compareEMI(200000.0, "home", 10);
        assertNotNull(emiMap);
        assertEquals(5, emiMap.size());

        // Optional: Check one known value
        double expectedSBI = emiService.EMI(200000.0, 7.8, 10);
        assertEquals(expectedSBI, emiMap.get("SBI"), 0.5);
    }

    @Test
    public void testCompareEMI_personalLoan() {
        Map<String, Double> emiMap = emiService.compareEMI(100000.0, "personal", 5);
        assertNotNull(emiMap);
        assertEquals(5, emiMap.size());

        double expectedICICI = emiService.EMI(100000.0, 12.5, 5);
        assertEquals(expectedICICI, emiMap.get("ICICI"), 0.5);
    }

    @Test
    public void testBestHomeLoan() {
        double principal = 100000;
        int time = 10;

        Map<String, Double> result = emiService.bestHOMELOAN(principal, time);
        assertNotNull(result);
        assertEquals(1, result.size());

        String bestBank = result.keySet().iterator().next();
        assertEquals("HDFC", bestBank); // HDFC has lowest rate (7.5) in your method

        double expectedEmi = emiService.EMI(principal, 7.5, time);
        assertEquals(expectedEmi, result.get(bestBank), 0.5);
    }

    @Test
    public void testBestPersonalLoan() {
        double principal = 100000;
        int time = 5;

        Map<String, Double> result = emiService.bestPERSONALLOAN(principal, time);
        assertNotNull(result);
        assertEquals(1, result.size());

        String bestBank = result.keySet().iterator().next();
        assertEquals("ICICI", bestBank); // ICICI has lowest personal loan rate (10.3)

        double expectedEmi = emiService.EMI(principal, 10.3, time);
        assertEquals(expectedEmi, result.get(bestBank), 0.5);
    }
}
