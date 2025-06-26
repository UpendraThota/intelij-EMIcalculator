package com.example.EMIcaluclator.service;

import java.util.HashMap;
import java.util.Map;

public class LoanRatesProvider
{
    public static Map<String, Double> getHomeLoanRates() {
        Map<String, Double> homeRates = new HashMap<>();
        homeRates.put("HDFC", 7.5);
        homeRates.put("SBI", 7.8);
        homeRates.put("LIC", 8.5);
        homeRates.put("AXIS", 8.7);
        homeRates.put("ICICI", 9.5);
        return homeRates;
    }

    public static Map<String, Double> getPersonalLoanRates() {
        Map<String, Double> personalRates = new HashMap<>();
        personalRates.put("HDFC", 10.5);
        personalRates.put("SBI", 11.2);
        personalRates.put("LIC", 12.0);
        personalRates.put("AXIS", 11.5);
        personalRates.put("ICICI", 10.3);
        return personalRates;
    }
}
