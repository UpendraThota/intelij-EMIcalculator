package com.example.EMIcaluclator.service;

import java.util.Map;

public interface callEMI {
    double EMI(double principal, double rate, int time);
    Map<String, Double> compareEMI(Double principal, String typeOfLoan, Integer time);

    Map<String, Double> bestHomeLoan(Double principal, Integer time);

    Map<String, Double> bestPersonalLoan(Double principal, Integer time);
}
