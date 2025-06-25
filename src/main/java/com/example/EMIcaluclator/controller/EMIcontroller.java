package com.example.EMIcaluclator.controller;

import com.example.EMIcaluclator.exception.principalAmountcannotbeNull;
import com.example.EMIcaluclator.exception.ratecannotbeNull;
import com.example.EMIcaluclator.exception.timecannotbeNull;
import com.example.EMIcaluclator.service.callEMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class EMIcontroller {

    @Autowired
    private callEMI callemi;

    @GetMapping("/calEMI")
    public double calEMI(@RequestParam(required = false) Double principal,
                         @RequestParam(required = false) Double rate,
                         @RequestParam(required = false) Integer time) {
        if (principal == null) {
            throw new principalAmountcannotbeNull("Amount should be more than 0");
        }
        if (rate == null) {
            throw new ratecannotbeNull("Rate should be more than 0");
        }
        if (time == null) {
            throw new timecannotbeNull("Time should be more than 0");
        }

        return callemi.EMI(principal, rate, time);
    }

    @GetMapping("/compareEMI")
    public Map<String, Double> compareEMI(@RequestParam(required = false) Double principal,
                                          @RequestParam(required = false) String typeOfLoan,
                                          @RequestParam(required = false) Integer time) {

        if (principal == null) {
            throw new principalAmountcannotbeNull("Amount should be more than 0");
        }

        return callemi.compareEMI(principal, typeOfLoan, time);
    }


    @GetMapping("/bestHomeLoan")
    public Map<String, Double> bestHomeLoan(@RequestParam(required = false) Double principal,
                                            @RequestParam(required = false) Integer time) {
        if (principal == null) {
            throw new principalAmountcannotbeNull("Principal amount should not be null");
        }
        if (time == null) {
            throw new timecannotbeNull("Time should not be null");
        }
        if(time==0)
        {
            throw new timecannotbeNull("Time cannot be zero");
        }

        return callemi.bestHOMELOAN(principal, time);
    }


    @GetMapping("/bestPersonalLoan")
    public Map<String, Double> bestPERSONALLOAN(@RequestParam(required = false) Double principal,
                                            @RequestParam(required = false) Integer time) {
        if (principal == null) {
            throw new principalAmountcannotbeNull("Principal amount should not be null");
        }
        if (time == null) {
            throw new timecannotbeNull("Time should not be null");
        }
        if(time==0)
        {
            throw new timecannotbeNull("Time cannot be zero");
        }

        return callemi.bestPERSONALLOAN(principal, time);
    }

}
