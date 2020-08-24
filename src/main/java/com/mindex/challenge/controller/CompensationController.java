package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    private CompensationService compensationService;

    @Autowired
    public CompensationController(CompensationService compensationService) {

        LOG.debug("Autowired instance of Compensation Service");

        this.compensationService = compensationService;
    }

    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {

        LOG.debug("Received compensation create request for [{}]", compensation.getEmployeeId());
        return compensationService.createCompensation(compensation);
    }

    @PutMapping("/compensation/{employeeId}")
    public Compensation updateCompensation(@PathVariable String employeeId, @RequestBody Compensation compensation) {

        LOG.debug("Received compensation update request for [{}]", employeeId);
        compensation.setEmployeeId(employeeId);
        return compensationService.updateCompensation(compensation);
    }

    @GetMapping("/compensation/{employeeId}")
    public Compensation readCompensation(@PathVariable String employeeId) {

        LOG.debug("Received compensation read request for [{}]", employeeId);
        Compensation compensations = compensationService.readCompensation(employeeId);
        System.out.println(compensations.toString());

        return compensationService.readCompensation(employeeId);
    }

}
