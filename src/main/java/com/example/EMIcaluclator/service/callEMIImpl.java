package com.example.EMIcaluclator.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class callEMIImpl implements callEMI {

    @Override
    public double EMI(double principal, double rate, int time) {
        double monthlyRate = rate / (12 * 100);
        int totalMonths = time * 12;

        double numerator = principal * monthlyRate * Math.pow(1 + monthlyRate, totalMonths);
        double denominator = Math.pow(1 + monthlyRate, totalMonths) - 1;

        return numerator / denominator;
    }

    @Override
    public Map<String, Double> compareEMI(Double principal, String typeOfLoan, Integer time) {
        Map<String, Double> rates = new HashMap<>();

        if ("home".equalsIgnoreCase(typeOfLoan)) {

            rates.put("HDFC", 7.5);
            rates.put("SBI", 7.8);
            rates.put("LIC", 8.5);
            rates.put("AXIS", 8.7);
            rates.put("ICICI", 9.5);
            if (time == null)
            {
                time = 10;
            }
        }
        else if ("personal".equalsIgnoreCase(typeOfLoan))
        {

            rates.put("HDFC", 10.5);
            rates.put("SBI", 11.2);
            rates.put("LIC", 12.0);
            rates.put("AXIS", 11.5);
            rates.put("ICICI", 12.5);
            if (time == null)
            {
                time = 5;
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid loan type: must be 'home' or 'personal'");
        }

        Map<String, Double> emiResults = new HashMap<>();
        for (String bank : rates.keySet()) {
            Double rate = rates.get(bank);
            double emi = EMI(principal, rate, time);
            emiResults.put(bank, emi);
        }

        return emiResults;
    }

    @Override
    public Map<String, Double> bestHOMELOAN(Double principal, Integer time) {
        Map<String, Double> rates = new HashMap<>();
        rates.put("HDFC", 7.5);
        rates.put("SBI", 7.8);
        rates.put("LIC", 8.5);
        rates.put("AXIS", 8.7);
        rates.put("ICICI", 9.5);

        String bestBank = "";
        double lowestRate = Double.MAX_VALUE;


        for (String bank : rates.keySet()) {
            double rate = rates.get(bank);
            if (rate < lowestRate) {
                lowestRate = rate;
                bestBank = bank;
            }
        }


       double res= EMI(principal, lowestRate, time);


        Map<String, Double> result = new HashMap<>();
        result.put(bestBank, res);
        return result;
    }



    @Override
    public Map<String, Double> bestPERSONALLOAN(Double principal, Integer time) {
        Map<String, Double> rates = new HashMap<>();
        rates.put("HDFC", 10.5);
        rates.put("SBI", 11.2);
        rates.put("LIC", 12.0);
        rates.put("AXIS", 11.5);
        rates.put("ICICI", 10.3);

        String bestBank = "";
        double lowestRate = Double.MAX_VALUE;


        for (String bank : rates.keySet()) {
            double rate = rates.get(bank);
            if (rate < lowestRate) {
                lowestRate = rate;
                bestBank = bank;
            }
        }


        double res= EMI(principal, lowestRate, time);


        Map<String, Double> result = new HashMap<>();
        result.put(bestBank, res);
        return result;
    }

}
